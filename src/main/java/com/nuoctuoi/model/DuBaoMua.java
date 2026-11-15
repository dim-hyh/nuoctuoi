package com.nuoctuoi.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// BM4 – Dự báo mưa theo vùng
@Entity
public class DuBaoMua {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String maVung;
    private LocalDate ngay;
    private double luongMua;   // mm

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMaVung() { return maVung; }
    public void setMaVung(String maVung) { this.maVung = maVung; }
    public LocalDate getNgay() { return ngay; }
    public void setNgay(LocalDate ngay) { this.ngay = ngay; }
    public double getLuongMua() { return luongMua; }
    public void setLuongMua(double luongMua) { this.luongMua = luongMua; }
}