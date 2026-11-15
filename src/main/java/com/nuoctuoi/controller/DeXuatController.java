package com.nuoctuoi.controller;

import com.nuoctuoi.dto.NguoiDungPhien;
import com.nuoctuoi.model.DeXuatTuoi;
import com.nuoctuoi.model.HangSo;
import com.nuoctuoi.model.HanMucNuoc;
import com.nuoctuoi.repository.DeXuatTuoiRepository;
import com.nuoctuoi.service.KetQua;
import com.nuoctuoi.service.XuLyDeXuat;
import com.nuoctuoi.service.XuLyGhiDe;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// UC09 – Lập đề xuất, UC10 – Tra cứu và xác nhận, UC11 – Ghi đè, UC13 – Ghi lượng thực tế
@Controller
@RequestMapping("/de-xuat")
public class DeXuatController {

    private final DeXuatTuoiRepository deXuatRepo;
    private final XuLyDeXuat xuLyDeXuat;
    private final XuLyGhiDe xuLyGhiDe;

    public DeXuatController(DeXuatTuoiRepository deXuatRepo, XuLyDeXuat xuLyDeXuat, XuLyGhiDe xuLyGhiDe) {
        this.deXuatRepo = deXuatRepo;
        this.xuLyDeXuat = xuLyDeXuat;
        this.xuLyGhiDe = xuLyGhiDe;
    }

    // Ngày lập tiếp theo = ngày của đề xuất mới nhất + 1 (chưa có đề xuất thì 06/10/2026)
    static LocalDate ngayTiepTheo(DeXuatTuoiRepository repo) {
        List<DeXuatTuoi> ds = repo.findAllByOrderByNgayLapDescMaDeXuatAsc();
        return ds.isEmpty() ? LocalDate.of(2026, 10, 6) : ds.get(0).getNgayLap().plusDays(1);
    }

    private void baoKetQua(KetQua kq, RedirectAttributes ra) {
        ra.addFlashAttribute(kq.isThanhCong() ? "thongBao" : "loi", kq.getThongBao());
    }

    @GetMapping
    public String danhSach(Model model) {
        model.addAttribute("dsDeXuat", deXuatRepo.findAllByOrderByNgayLapDescMaDeXuatAsc());
        model.addAttribute("choXacNhan", HangSo.DX_CHO_XAC_NHAN);
        model.addAttribute("daXacNhan", HangSo.DX_DA_XAC_NHAN);
        model.addAttribute("daGhiDe", HangSo.DX_DA_GHI_DE);
        // Ô "Ngày lập" điền sẵn = ngày sau đề xuất mới nhất
        model.addAttribute("ngayMacDinh", ngayTiepTheo(deXuatRepo).toString());
        return "de-xuat";
    }

    // Cán bộ kỹ thuật bấm để chạy thay cho bộ hẹn giờ lúc 05:00
    @PostMapping("/lap")
    public String lap(@RequestParam(name = "ngay", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate ngay,
                      RedirectAttributes ra) {
        if (ngay == null) {
            ra.addFlashAttribute("loi", "Chưa chọn ngày lập.");
        } else {
            List<String> kq = xuLyDeXuat.lapDeXuat(ngay);
            ra.addFlashAttribute("thongBao", String.join(" ", kq));
            ra.addFlashAttribute("ngayLap", ngay.toString());   // giữ lại ngày vừa chọn
        }
        return "redirect:/de-xuat";
    }

    @PostMapping("/xac-nhan")
    public String xacNhan(@RequestParam("ma") String ma, RedirectAttributes ra) {
        baoKetQua(xuLyDeXuat.xacNhan(ma), ra);
        return "redirect:/de-xuat";
    }

    @GetMapping("/ghi-de")
    public String ghiDe(@RequestParam("ma") String ma, Model model) {
        DeXuatTuoi dx = deXuatRepo.findById(ma).orElse(null);
        if (dx == null) return "redirect:/de-xuat";
        HanMucNuoc hm = xuLyDeXuat.timHanMuc(dx.getMaVung(), dx.getNgayLap());
        model.addAttribute("dx", dx);
        model.addAttribute("conLai", hm != null ? hm.getConLai() : 0.0);
        model.addAttribute("moTaToiThieu", xuLyDeXuat.layQuyDinh().getMoTaToiThieu());
        model.addAttribute("dsLyDo", HangSo.LY_DO_GHI_DE);
        return "ghi-de";
    }

    @PostMapping("/ghi-de")
    public String ghiDe(@RequestParam("ma") String ma, @RequestParam("lyDo") String lyDo, @RequestParam("moTa") String moTa,
                        @RequestParam(name = "luongMoi", required = false) Double luongMoi,
                        HttpSession session, RedirectAttributes ra) {
        NguoiDungPhien nd = (NguoiDungPhien) session.getAttribute("nd");
        KetQua kq = xuLyGhiDe.ghiDe(ma, lyDo, moTa, luongMoi, nd.getHoTen());
        baoKetQua(kq, ra);
        if (!kq.isThanhCong()) {
            ra.addAttribute("ma", ma);   // để Spring tự mã hóa chữ "Đ" trong mã đề xuất
            return "redirect:/de-xuat/ghi-de";
        }
        return "redirect:/de-xuat";
    }

    @PostMapping("/thuc-te")
    public String thucTe(@RequestParam("ma") String ma, @RequestParam(name = "luong", required = false) Double luong,
                         RedirectAttributes ra) {
        baoKetQua(xuLyDeXuat.ghiThucTe(ma, luong), ra);
        return "redirect:/de-xuat";
    }
}