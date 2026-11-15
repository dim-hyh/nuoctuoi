package com.nuoctuoi.controller;

import com.nuoctuoi.model.HanMucNuoc;
import com.nuoctuoi.repository.HanMucNuocRepository;
import com.nuoctuoi.repository.VungTuoiRepository;
import com.nuoctuoi.service.XuLyDeXuat;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// UC08 – Cấp hạn mức nước (BM5)
@Controller
@RequestMapping("/han-muc")
public class HanMucController {

    private final HanMucNuocRepository hanMucRepo;
    private final VungTuoiRepository vungRepo;
    private final XuLyDeXuat xuLyDeXuat;

    public HanMucController(HanMucNuocRepository hanMucRepo, VungTuoiRepository vungRepo, XuLyDeXuat xuLyDeXuat) {
        this.hanMucRepo = hanMucRepo;
        this.vungRepo = vungRepo;
        this.xuLyDeXuat = xuLyDeXuat;
    }

    @GetMapping
    public String danhSach(Model model) {
        model.addAttribute("dsHanMuc", hanMucRepo.findAllByOrderByMaHanMuc());
        model.addAttribute("dsVung", vungRepo.findAllByOrderByMaVung());
        model.addAttribute("qd", xuLyDeXuat.layQuyDinh());
        return "han-muc";
    }

    @PostMapping("/them")
    public String them(@RequestParam("maVung") String maVung,
                       @RequestParam(name = "tuNgay", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate tuNgay,
                       @RequestParam(name = "denNgay", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate denNgay,
                       @RequestParam(name = "hanMuc", required = false) Double hanMuc,
                       RedirectAttributes ra) {
        if (tuNgay == null || denNgay == null || tuNgay.isAfter(denNgay)) {
            ra.addFlashAttribute("loi", "Ngày không hợp lệ.");
        } else if (hanMuc == null || hanMuc <= 0) {
            ra.addFlashAttribute("loi", "Hạn mức phải lớn hơn 0 (QĐ5).");
        } else if (hanMucRepo.existsByMaVungAndTuNgayLessThanEqualAndDenNgayGreaterThanEqual(maVung, denNgay, tuNgay)) {
            ra.addFlashAttribute("loi", "Kỳ hạn mức bị trùng thời gian với kỳ đã có (QĐ5).");
        } else {
            HanMucNuoc hm = new HanMucNuoc();
            hm.setMaHanMuc(String.format("HM-%02d", hanMucRepo.count() + 1));
            hm.setMaVung(maVung);
            hm.setTuNgay(tuNgay);
            hm.setDenNgay(denNgay);
            hm.setHanMuc(hanMuc);
            hm.setDaDung(0);
            hanMucRepo.save(hm);
            ra.addFlashAttribute("thongBao", "Đã cấp hạn mức " + hm.getMaHanMuc() + ".");
        }
        return "redirect:/han-muc";
    }

    // Bổ sung thêm nước vào kỳ hạn mức đang có (khi HTX được cấp thêm nước)
    @PostMapping("/bo-sung")
    public String boSung(@RequestParam("maHanMuc") String maHanMuc,
                         @RequestParam(name = "luongThem", required = false) Double luongThem,
                         RedirectAttributes ra) {
        HanMucNuoc hm = hanMucRepo.findById(maHanMuc).orElse(null);
        if (hm == null) {
            ra.addFlashAttribute("loi", "Không tìm thấy kỳ hạn mức.");
        } else if (luongThem == null || luongThem <= 0) {
            ra.addFlashAttribute("loi", "Lượng bổ sung phải lớn hơn 0.");
        } else {
            hm.setHanMuc(hm.getHanMuc() + luongThem);
            hanMucRepo.save(hm);
            ra.addFlashAttribute("thongBao", "Đã bổ sung " + luongThem + " m³ cho " + maHanMuc
                    + ". Hạn mức mới: " + hm.getHanMuc() + " m³.");
        }
        return "redirect:/han-muc";
    }
}