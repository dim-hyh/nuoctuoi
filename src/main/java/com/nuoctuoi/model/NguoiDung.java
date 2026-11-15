package com.nuoctuoi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Người dùng hệ thống
@Entity
public class NguoiDung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tenDangNhap;
    private String matKhauHash;   // chỉ lưu mã băm, không lưu mật khẩu gốc
    private String hoTen;
    private String vaiTro;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTenDangNhap() { return tenDangNhap; }
    public void setTenDangNhap(String tenDangNhap) { this.tenDangNhap = tenDangNhap; }
    public String getMatKhauHash() { return matKhauHash; }
    public void setMatKhauHash(String matKhauHash) { this.matKhauHash = matKhauHash; }
    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    public String getVaiTro() { return vaiTro; }
    public void setVaiTro(String vaiTro) { this.vaiTro = vaiTro; }
}