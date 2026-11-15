package com.nuoctuoi.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// BM3 – Lịch mùa vụ
@Entity
public class LichMuaVu {

    @Id
    private String maLich;
    private String maCay;
    private LocalDate ngayGieo;
    private String dsThua;   // danh sách thửa để hiển thị, ví dụ "T-014, T-015"

    public String getMaLich() { return maLich; }
    public void setMaLich(String maLich) { this.maLich = maLich; }
    public String getMaCay() { return maCay; }
    public void setMaCay(String maCay) { this.maCay = maCay; }
    public LocalDate getNgayGieo() { return ngayGieo; }
    public void setNgayGieo(LocalDate ngayGieo) { this.ngayGieo = ngayGieo; }
    public String getDsThua() { return dsThua; }
    public void setDsThua(String dsThua) { this.dsThua = dsThua; }
}