package com.nuoctuoi.controller;

import com.nuoctuoi.dto.NguoiDungPhien;
import com.nuoctuoi.model.HangSo;
import com.nuoctuoi.model.NguoiDung;
import com.nuoctuoi.repository.DeXuatTuoiRepository;
import com.nuoctuoi.repository.HanMucNuocRepository;
import com.nuoctuoi.repository.NguoiDungRepository;
import com.nuoctuoi.repository.PhieuGhiDeRepository;
import com.nuoctuoi.service.MatKhau;
import com.nuoctuoi.service.XuLyDeXuat;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    private final NguoiDungRepository nguoiDungRepo;
    private final DeXuatTuoiRepository deXuatRepo;
    private final PhieuGhiDeRepository phieuRepo;
    private final HanMucNuocRepository hanMucRepo;
    private final XuLyDeXuat xuLyDeXuat;

    public HomeController(NguoiDungRepository nguoiDungRepo, DeXuatTuoiRepository deXuatRepo,
                          PhieuGhiDeRepository phieuRepo, HanMucNuocRepository hanMucRepo,
                          XuLyDeXuat xuLyDeXuat) {
        this.nguoiDungRepo = nguoiDungRepo;
        this.deXuatRepo = deXuatRepo;
        this.phieuRepo = phieuRepo;
        this.hanMucRepo = hanMucRepo;
        this.xuLyDeXuat = xuLyDeXuat;
    }

    // Màn hình chính
    @GetMapping("/")
    public String trangChu(Model model) {
        model.addAttribute("soChoXacNhan", deXuatRepo.countByTrangThai(HangSo.DX_CHO_XAC_NHAN));
        model.addAttribute("soChoDuyet", phieuRepo.countByTrangThai(HangSo.PGD_CHO_DUYET));
        model.addAttribute("dsHanMuc", hanMucRepo.findAllByOrderByMaHanMuc());
        model.addAttribute("qd", xuLyDeXuat.layQuyDinh());
        return "index";
    }

    // UC01 – Đăng nhập (Spring Security xử lý khi bấm nút, controller chỉ hiện trang)
    @GetMapping("/dang-nhap")
    public String dangNhap(@RequestParam(name = "loi", required = false) String loi, Model model) {
        if (loi != null) {
            model.addAttribute("loi", "Sai tên đăng nhập hoặc mật khẩu.");
        }
        return "dang-nhap";
    }

    // UC02 – Đăng xuất: do Spring Security xử lý tại /dang-xuat (xem SecurityConfig)

    // UC03 – Đổi mật khẩu
    @GetMapping("/doi-mat-khau")
    public String doiMatKhau() {
        return "doi-mat-khau";
    }

    @PostMapping("/doi-mat-khau")
    public String doiMatKhau(@RequestParam("matKhauCu") String matKhauCu, @RequestParam("matKhauMoi") String matKhauMoi,
                             @RequestParam("nhapLai") String nhapLai, HttpSession session, RedirectAttributes ra) {
        NguoiDungPhien phien = (NguoiDungPhien) session.getAttribute("nd");
        NguoiDung nd = nguoiDungRepo.findByTenDangNhap(phien.getTenDangNhap()).orElseThrow();
        if (!MatKhau.khop(matKhauCu, nd.getMatKhauHash())) {
            ra.addFlashAttribute("loi", "Mật khẩu cũ không đúng.");
        } else if (matKhauMoi.length() < 3) {
            ra.addFlashAttribute("loi", "Mật khẩu mới phải có ít nhất 3 ký tự.");
        } else if (!matKhauMoi.equals(nhapLai)) {
            ra.addFlashAttribute("loi", "Nhập lại mật khẩu không khớp.");
        } else {
            nd.setMatKhauHash(MatKhau.bam(matKhauMoi));
            nguoiDungRepo.save(nd);
            ra.addFlashAttribute("thongBao", "Đã đổi mật khẩu.");
        }
        return "redirect:/doi-mat-khau";
    }

    @GetMapping("/khong-co-quyen")
    public String khongCoQuyen() {
        return "khong-co-quyen";
    }
}