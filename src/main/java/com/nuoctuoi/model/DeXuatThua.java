package com.nuoctuoi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Bảng trung gian: một đề xuất áp dụng cho nhiều thửa
@Entity
public class DeXuatThua {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String maDeXuat;
    private String maThua;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMaDeXuat() { return maDeXuat; }
    public void setMaDeXuat(String maDeXuat) { this.maDeXuat = maDeXuat; }
    public String getMaThua() { return maThua; }
    public void setMaThua(String maThua) { this.maThua = maThua; }
}