package com.nuoctuoi.controller;

import com.nuoctuoi.model.HangSo;
import com.nuoctuoi.model.ThuaRuong;
import com.nuoctuoi.repository.LichMuaVuThuaRepository;
import com.nuoctuoi.repository.ThuaRuongRepository;
import com.nuoctuoi.repository.VungTuoiRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// UC04 – Quản lý thửa ruộng (BM1)
@Controller
@RequestMapping("/thua-ruong")
public class ThuaRuongController {

    private final ThuaRuongRepository thuaRepo;
    private final VungTuoiRepository vungRepo;
    private final LichMuaVuThuaRepository lichThuaRepo;

    public ThuaRuongController(ThuaRuongRepository thuaRepo, VungTuoiRepository vungRepo,
                               LichMuaVuThuaRepository lichThuaRepo) {
        this.thuaRepo = thuaRepo;
        this.vungRepo = vungRepo;
        this.lichThuaRepo = lichThuaRepo;
    }

    @GetMapping
    public String danhSach(Model model) {
        model.addAttribute("dsThua", thuaRepo.findAllByOrderByMaThua());
        model.addAttribute("dsVung", vungRepo.findAllByOrderByMaVung());
        model.addAttribute("dsHinhThuc", HangSo.HINH_THUC_TUOI);
        return "thua-ruong";
    }

    @PostMapping("/them")
    public String them(@RequestParam("maThua") String maThua, @RequestParam("maVung") String maVung,
                       @RequestParam("chuHo") String chuHo,
                       @RequestParam(name = "dienTich", required = false) Double dienTich,
                       @RequestParam("loaiDat") String loaiDat,
                       @RequestParam("hinhThucTuoi") String hinhThucTuoi, RedirectAttributes ra) {
        maThua = maThua.trim();
        if (maThua.isEmpty() || chuHo.isBlank()) {
            ra.addFlashAttribute("loi", "Phải nhập mã thửa và chủ hộ.");
        } else if (thuaRepo.existsById(maThua)) {
            ra.addFlashAttribute("loi", "Mã thửa đã tồn tại.");
        } else if (dienTich == null || dienTich <= 0) {
            ra.addFlashAttribute("loi", "Diện tích phải lớn hơn 0 (QĐ1).");
        } else {
            ThuaRuong t = new ThuaRuong();
            t.setMaThua(maThua);
            t.setMaVung(maVung);
            t.setChuHo(chuHo.trim());
            t.setDienTich(dienTich);
            t.setLoaiDat(loaiDat.trim());
            t.setHinhThucTuoi(hinhThucTuoi);
            thuaRepo.save(t);
            ra.addFlashAttribute("thongBao", "Đã thêm thửa " + maThua + ".");
        }
        return "redirect:/thua-ruong";
    }

    @PostMapping("/xoa")
    public String xoa(@RequestParam("maThua") String maThua, RedirectAttributes ra) {
        if (lichThuaRepo.existsByMaThua(maThua)) {
            ra.addFlashAttribute("loi", "Thửa " + maThua + " đang có lịch mùa vụ, không xóa được.");
        } else {
            thuaRepo.deleteById(maThua);
            ra.addFlashAttribute("thongBao", "Đã xóa thửa " + maThua + ".");
        }
        return "redirect:/thua-ruong";
    }
}