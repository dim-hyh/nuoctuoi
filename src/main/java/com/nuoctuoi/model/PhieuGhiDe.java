package com.nuoctuoi.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// BM7 – Phiếu ghi đè có lý do
@Entity
public class PhieuGhiDe {

    @Id
    private String maPhieu;
    private String maDeXuat;
    private String nguoiGhiDe;
    private LocalDateTime thoiDiem;
    private String lyDo;
    private String moTa;
    private double luongGoc;
    private double luongMoi;
    private String trangThai;
    private Double luongDuyet;
    private String yKien;
    private String nguoiDuyet;

    public String getMaPhieu() { return maPhieu; }
    public void setMaPhieu(String maPhieu) { this.maPhieu = maPhieu; }
    public String getMaDeXuat() { return maDeXuat; }
    public void setMaDeXuat(String maDeXuat) { this.maDeXuat = maDeXuat; }
    public String getNguoiGhiDe() { return nguoiGhiDe; }
    public void setNguoiGhiDe(String nguoiGhiDe) { this.nguoiGhiDe = nguoiGhiDe; }
    public LocalDateTime getThoiDiem() { return thoiDiem; }
    public void setThoiDiem(LocalDateTime thoiDiem) { this.thoiDiem = thoiDiem; }
    public String getLyDo() { return lyDo; }
    public void setLyDo(String lyDo) { this.lyDo = lyDo; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
    public double getLuongGoc() { return luongGoc; }
    public void setLuongGoc(double luongGoc) { this.luongGoc = luongGoc; }
    public double getLuongMoi() { return luongMoi; }
    public void setLuongMoi(double luongMoi) { this.luongMoi = luongMoi; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
    public Double getLuongDuyet() { return luongDuyet; }
    public void setLuongDuyet(Double luongDuyet) { this.luongDuyet = luongDuyet; }
    public String getYKien() { return yKien; }
    public void setYKien(String yKien) { this.yKien = yKien; }
    public String getNguoiDuyet() { return nguoiDuyet; }
    public void setNguoiDuyet(String nguoiDuyet) { this.nguoiDuyet = nguoiDuyet; }
}