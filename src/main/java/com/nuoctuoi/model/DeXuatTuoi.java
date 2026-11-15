package com.nuoctuoi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

// BM6 – Đề xuất tưới (có phạm vi áp dụng)
@Entity
public class DeXuatTuoi {

    @Id
    private String maDeXuat;
    private String maLich;
    private String maVung;
    private LocalDate ngayLap;
    private String giaiDoan;
    private double luongGoc;   // lượng hệ thống tính
    private double luongApDung;   // lượng sau ghi đè / duyệt
    private String khungGio;
    private LocalDateTime hieuLucDen;
    private String canCu;   // số đo dùng để tính
    private String dsThua;   // phạm vi áp dụng: các thửa
    private String trangThai;
    private Double luongThucTe;
    private double luongGiuCho;   // phần đã giữ trong hạn mức
    private String canCuSo;   // độ ẩm căn cứ từng thửa, ví dụ "T-014=22.0;T-015=22.0"

    public String getMaDeXuat() { return maDeXuat; }
    public void setMaDeXuat(String maDeXuat) { this.maDeXuat = maDeXuat; }
    public String getMaLich() { return maLich; }
    public void setMaLich(String maLich) { this.maLich = maLich; }
    public String getMaVung() { return maVung; }
    public void setMaVung(String maVung) { this.maVung = maVung; }
    public LocalDate getNgayLap() { return ngayLap; }
    public void setNgayLap(LocalDate ngayLap) { this.ngayLap = ngayLap; }
    public String getGiaiDoan() { return giaiDoan; }
    public void setGiaiDoan(String giaiDoan) { this.giaiDoan = giaiDoan; }
    public double getLuongGoc() { return luongGoc; }
    public void setLuongGoc(double luongGoc) { this.luongGoc = luongGoc; }
    public double getLuongApDung() { return luongApDung; }
    public void setLuongApDung(double luongApDung) { this.luongApDung = luongApDung; }
    public String getKhungGio() { return khungGio; }
    public void setKhungGio(String khungGio) { this.khungGio = khungGio; }
    public LocalDateTime getHieuLucDen() { return hieuLucDen; }
    public void setHieuLucDen(LocalDateTime hieuLucDen) { this.hieuLucDen = hieuLucDen; }
    public String getCanCu() { return canCu; }
    public void setCanCu(String canCu) { this.canCu = canCu; }
    public String getDsThua() { return dsThua; }
    public void setDsThua(String dsThua) { this.dsThua = dsThua; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
    public Double getLuongThucTe() { return luongThucTe; }
    public void setLuongThucTe(Double luongThucTe) { this.luongThucTe = luongThucTe; }
    public double getLuongGiuCho() { return luongGiuCho; }
    public void setLuongGiuCho(double luongGiuCho) { this.luongGiuCho = luongGiuCho; }
    public String getCanCuSo() { return canCuSo; }
    public void setCanCuSo(String canCuSo) { this.canCuSo = canCuSo; }
}