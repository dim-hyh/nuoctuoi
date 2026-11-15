package com.nuoctuoi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// BM1 – Hồ sơ thửa ruộng
@Entity
public class ThuaRuong {

    @Id
    private String maThua;
    private String maVung;
    private String chuHo;
    private double dienTich;   // m²
    private String loaiDat;
    private String hinhThucTuoi;   // Nhỏ giọt / Phun mưa / Rãnh

    public String getMaThua() { return maThua; }
    public void setMaThua(String maThua) { this.maThua = maThua; }
    public String getMaVung() { return maVung; }
    public void setMaVung(String maVung) { this.maVung = maVung; }
    public String getChuHo() { return chuHo; }
    public void setChuHo(String chuHo) { this.chuHo = chuHo; }
    public double getDienTich() { return dienTich; }
    public void setDienTich(double dienTich) { this.dienTich = dienTich; }
    public String getLoaiDat() { return loaiDat; }
    public void setLoaiDat(String loaiDat) { this.loaiDat = loaiDat; }
    public String getHinhThucTuoi() { return hinhThucTuoi; }
    public void setHinhThucTuoi(String hinhThucTuoi) { this.hinhThucTuoi = hinhThucTuoi; }

    // Hiệu suất tưới theo hình thức tưới (QĐ6)
    public double hieuSuat(QuyDinh qd) {
        if ("Nhỏ giọt".equals(hinhThucTuoi)) return qd.getHsNhoGiot();
        if ("Phun mưa".equals(hinhThucTuoi)) return qd.getHsPhunMua();
        return qd.getHsRanh();
    }
}