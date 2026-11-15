package com.nuoctuoi.model;

import com.nuoctuoi.config.NgayHeThong;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

// BM5 – Hạn mức nước của vùng trong một kỳ
@Entity
public class HanMucNuoc {

    @Id
    private String maHanMuc;
    private String maVung;
    private LocalDate tuNgay;
    private LocalDate denNgay;
    private double hanMuc;   // m³
    private double daDung;   // m³, gồm cả phần giữ chỗ

    public String getMaHanMuc() { return maHanMuc; }
    public void setMaHanMuc(String maHanMuc) { this.maHanMuc = maHanMuc; }
    public String getMaVung() { return maVung; }
    public void setMaVung(String maVung) { this.maVung = maVung; }
    public LocalDate getTuNgay() { return tuNgay; }
    public void setTuNgay(LocalDate tuNgay) { this.tuNgay = tuNgay; }
    public LocalDate getDenNgay() { return denNgay; }
    public void setDenNgay(LocalDate denNgay) { this.denNgay = denNgay; }
    public double getHanMuc() { return hanMuc; }
    public void setHanMuc(double hanMuc) { this.hanMuc = hanMuc; }
    public double getDaDung() { return daDung; }
    public void setDaDung(double daDung) { this.daDung = daDung; }

    public double getConLai() {
        return hanMuc - daDung;
    }

    // QĐ5: cảnh báo khi phần còn lại dưới x% hạn mức; còn 0 thì báo Đã hết; qua ngày kết thúc kỳ thì Đã kết thúc
    public String trangThai(QuyDinh qd) {
        if (denNgay != null && NgayHeThong.homNay().isAfter(denNgay)) return "Đã kết thúc";
        double conLai = Math.round(getConLai() * 10) / 10.0;   // làm tròn 0,1 m³ cho khỏi lệch số lẻ
        if (conLai < 0) return "Vượt hạn mức";
        if (conLai == 0) return "Đã hết";
        if (conLai < qd.getCanhBaoPhanTram() / 100 * hanMuc) return "Sắp hết";
        return "Bình thường";
    }
}