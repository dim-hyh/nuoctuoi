package com.nuoctuoi.controller;

import com.nuoctuoi.dto.NguoiDungPhien;
import com.nuoctuoi.model.HangSo;
import com.nuoctuoi.model.PhieuGhiDe;
import com.nuoctuoi.repository.PhieuGhiDeRepository;
import com.nuoctuoi.service.KetQua;
import com.nuoctuoi.service.XuLyGhiDe;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// UC12 – Phê duyệt ghi đè
@Controller
@RequestMapping("/phe-duyet")
public class PheDuyetController {

    private final PhieuGhiDeRepository phieuRepo;
    private final XuLyGhiDe xuLyGhiDe;

    public PheDuyetController(PhieuGhiDeRepository phieuRepo, XuLyGhiDe xuLyGhiDe) {
        this.phieuRepo = phieuRepo;
        this.xuLyGhiDe = xuLyGhiDe;
    }

    @GetMapping
    public String danhSach(Model model) {
        model.addAttribute("dsPhieu", phieuRepo.findAllByOrderByThoiDiemDesc());
        model.addAttribute("choDuyet", HangSo.PGD_CHO_DUYET);
        return "phe-duyet";
    }

    @GetMapping("/duyet")
    public String duyet(@RequestParam("ma") String ma, Model model) {
        PhieuGhiDe phieu = phieuRepo.findById(ma).orElse(null);
        if (phieu == null) return "redirect:/phe-duyet";
        model.addAttribute("phieu", phieu);
        double toiDa = xuLyGhiDe.luongToiDa(phieu);
        model.addAttribute("luongToiDa", toiDa);
        // Lượng xin vượt mức tối đa -> giao diện chọn sẵn "Điều chỉnh"
        model.addAttribute("vuot", phieu.getLuongMoi() > toiDa + 0.001);
        return "duyet";
    }

    @PostMapping("/duyet")
    public String duyet(@RequestParam("ma") String ma, @RequestParam("quyetDinh") String quyetDinh,
                        @RequestParam(name = "luongDuyet", required = false) Double luongDuyet, @RequestParam("yKien") String yKien,
                        HttpSession session, RedirectAttributes ra) {
        NguoiDungPhien nd = (NguoiDungPhien) session.getAttribute("nd");
        KetQua kq = xuLyGhiDe.duyet(ma, quyetDinh, luongDuyet, yKien, nd.getHoTen());
        ra.addFlashAttribute(kq.isThanhCong() ? "thongBao" : "loi", kq.getThongBao());
        if (!kq.isThanhCong()) {
            ra.addAttribute("ma", ma);   // để Spring tự mã hóa chữ "Đ" trong mã phiếu
            return "redirect:/phe-duyet/duyet";
        }
        return "redirect:/phe-duyet";
    }
}