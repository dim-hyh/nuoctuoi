package com.nuoctuoi.controller;

import com.nuoctuoi.dto.NguoiDungPhien;
import com.nuoctuoi.model.HangSo;
import com.nuoctuoi.model.NguoiDung;
import com.nuoctuoi.repository.NguoiDungRepository;
import com.nuoctuoi.service.MatKhau;
import jakarta.servlet.http.HttpSession;
import java.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Quản trị hệ thống – Quản lý tài khoản người dùng
@Controller
@RequestMapping("/tai-khoan")
public class TaiKhoanController {

    private final NguoiDungRepository nguoiDungRepo;

    public TaiKhoanController(NguoiDungRepository nguoiDungRepo) {
        this.nguoiDungRepo = nguoiDungRepo;
    }

    @GetMapping
    public String danhSach(Model model) {
        model.addAttribute("dsNguoiDung", nguoiDungRepo.findAllByOrderByVaiTroAscTenDangNhapAsc());
        model.addAttribute("dsVaiTro", HangSo.DS_VAI_TRO);
        return "tai-khoan";
    }

    @PostMapping("/them")
    public String them(@RequestParam("tenDangNhap") String tenDangNhap, @RequestParam("hoTen") String hoTen,
                       @RequestParam("vaiTro") String vaiTro, @RequestParam("matKhau") String matKhau,
                       RedirectAttributes ra) {
        tenDangNhap = tenDangNhap.trim();
        if (tenDangNhap.isEmpty() || hoTen.isBlank()) {
            ra.addFlashAttribute("loi", "Phải nhập tên đăng nhập và họ tên.");
        } else if (nguoiDungRepo.existsByTenDangNhap(tenDangNhap)) {
            ra.addFlashAttribute("loi", "Tên đăng nhập đã tồn tại.");
        } else if (!Arrays.asList(HangSo.DS_VAI_TRO).contains(vaiTro)) {
            ra.addFlashAttribute("loi", "Vai trò không hợp lệ.");
        } else if (matKhau.length() < 3) {
            ra.addFlashAttribute("loi", "Mật khẩu phải có ít nhất 3 ký tự.");
        } else {
            NguoiDung nd = new NguoiDung();
            nd.setTenDangNhap(tenDangNhap);
            nd.setHoTen(hoTen.trim());
            nd.setVaiTro(vaiTro);
            nd.setMatKhauHash(MatKhau.bam(matKhau));
            nguoiDungRepo.save(nd);
            ra.addFlashAttribute("thongBao", "Đã thêm tài khoản " + tenDangNhap + ".");
        }
        return "redirect:/tai-khoan";
    }

    @PostMapping("/dat-lai-mat-khau")
    public String datLaiMatKhau(@RequestParam("id") Long id, @RequestParam("matKhauMoi") String matKhauMoi,
                                RedirectAttributes ra) {
        NguoiDung nd = nguoiDungRepo.findById(id).orElse(null);
        if (nd == null) {
            ra.addFlashAttribute("loi", "Không tìm thấy tài khoản.");
        } else if (matKhauMoi.length() < 3) {
            ra.addFlashAttribute("loi", "Mật khẩu mới phải có ít nhất 3 ký tự.");
        } else {
            nd.setMatKhauHash(MatKhau.bam(matKhauMoi));
            nguoiDungRepo.save(nd);
            ra.addFlashAttribute("thongBao", "Đã đặt lại mật khẩu cho " + nd.getTenDangNhap() + ".");
        }
        return "redirect:/tai-khoan";
    }

    @PostMapping("/xoa")
    public String xoa(@RequestParam("id") Long id, HttpSession session, RedirectAttributes ra) {
        NguoiDungPhien phien = (NguoiDungPhien) session.getAttribute("nd");
        NguoiDung nd = nguoiDungRepo.findById(id).orElse(null);
        if (nd == null) {
            ra.addFlashAttribute("loi", "Không tìm thấy tài khoản.");
        } else if (nd.getTenDangNhap().equals(phien.getTenDangNhap())) {
            ra.addFlashAttribute("loi", "Không thể xóa tài khoản đang đăng nhập.");
        } else {
            nguoiDungRepo.deleteById(id);
            ra.addFlashAttribute("thongBao", "Đã xóa tài khoản " + nd.getTenDangNhap() + ".");
        }
        return "redirect:/tai-khoan";
    }
}