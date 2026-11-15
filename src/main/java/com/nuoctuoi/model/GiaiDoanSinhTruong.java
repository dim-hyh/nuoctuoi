package com.nuoctuoi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// BM2 – Giai đoạn sinh trưởng
@Entity
public class GiaiDoanSinhTruong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String maCay;
    private int thuTu;
    private String tenGiaiDoan;
    private int soNgay;
    private double nguongDuoi;   // % – thấp hơn mức này thì cần tưới
    private double nguongTren;   // % – tưới lên tới mức này
    private double doSauRe;   // m

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMaCay() { return maCay; }
    public void setMaCay(String maCay) { this.maCay = maCay; }
    public int getThuTu() { return thuTu; }
    public void setThuTu(int thuTu) { this.thuTu = thuTu; }
    public String getTenGiaiDoan() { return tenGiaiDoan; }
    public void setTenGiaiDoan(String tenGiaiDoan) { this.tenGiaiDoan = tenGiaiDoan; }
    public int getSoNgay() { return soNgay; }
    public void setSoNgay(int soNgay) { this.soNgay = soNgay; }
    public double getNguongDuoi() { return nguongDuoi; }
    public void setNguongDuoi(double nguongDuoi) { this.nguongDuoi = nguongDuoi; }
    public double getNguongTren() { return nguongTren; }
    public void setNguongTren(double nguongTren) { this.nguongTren = nguongTren; }
    public double getDoSauRe() { return doSauRe; }
    public void setDoSauRe(double doSauRe) { this.doSauRe = doSauRe; }
}