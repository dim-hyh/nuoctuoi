package com.nuoctuoi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// BM2 – Cây trồng
@Entity
public class CayTrong {

    @Id
    private String maCay;
    private String tenCay;

    public String getMaCay() { return maCay; }
    public void setMaCay(String maCay) { this.maCay = maCay; }
    public String getTenCay() { return tenCay; }
    public void setTenCay(String tenCay) { this.tenCay = tenCay; }
}