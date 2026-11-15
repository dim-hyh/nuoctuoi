package com.nuoctuoi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Bảng trung gian: một lịch áp dụng cho nhiều thửa
@Entity
public class LichMuaVuThua {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String maLich;
    private String maThua;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMaLich() { return maLich; }
    public void setMaLich(String maLich) { this.maLich = maLich; }
    public String getMaThua() { return maThua; }
    public void setMaThua(String maThua) { this.maThua = maThua; }
}