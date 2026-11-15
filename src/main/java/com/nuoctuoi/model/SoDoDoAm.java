package com.nuoctuoi.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// BM4 – Số đo độ ẩm đất
@Entity
public class SoDoDoAm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String maThua;
    private LocalDateTime thoiDiem;
    private double doAm;   // %
    private String nguoiGhi;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMaThua() { return maThua; }
    public void setMaThua(String maThua) { this.maThua = maThua; }
    public LocalDateTime getThoiDiem() { return thoiDiem; }
    public void setThoiDiem(LocalDateTime thoiDiem) { this.thoiDiem = thoiDiem; }
    public double getDoAm() { return doAm; }
    public void setDoAm(double doAm) { this.doAm = doAm; }
    public String getNguoiGhi() { return nguoiGhi; }
    public void setNguoiGhi(String nguoiGhi) { this.nguoiGhi = nguoiGhi; }
}