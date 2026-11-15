package com.nuoctuoi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

// Các tham số quy định (QĐ11 – có thể thay đổi)
@Entity
public class QuyDinh {

    @Id
    private Long id;
    private int gioLap;   // QĐ6: giờ lập đề xuất
    private int hanSoDoGio;   // QĐ4: số đo cũ hơn x giờ thì không dùng
    private int hieuLucGio;   // QĐ6: hiệu lực đề xuất
    private double canhBaoPhanTram;   // QĐ5
    private double heSoMua;   // QĐ4: hệ số mưa hữu hiệu
    private int moTaToiThieu;   // QĐ8
    private double hsNhoGiot;
    private double hsPhunMua;
    private double hsRanh;
    private Double mucLechThayThe;   // QĐ6: số đo mới lệch quá bao nhiêu điểm % thì thay đề xuất
    private LocalDateTime lanLapGanNhat;   // mốc của lần lập đề xuất gần nhất (bộ hẹn giờ)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getGioLap() { return gioLap; }
    public void setGioLap(int gioLap) { this.gioLap = gioLap; }
    public int getHanSoDoGio() { return hanSoDoGio; }
    public void setHanSoDoGio(int hanSoDoGio) { this.hanSoDoGio = hanSoDoGio; }
    public int getHieuLucGio() { return hieuLucGio; }
    public void setHieuLucGio(int hieuLucGio) { this.hieuLucGio = hieuLucGio; }
    public double getCanhBaoPhanTram() { return canhBaoPhanTram; }
    public void setCanhBaoPhanTram(double canhBaoPhanTram) { this.canhBaoPhanTram = canhBaoPhanTram; }
    public double getHeSoMua() { return heSoMua; }
    public void setHeSoMua(double heSoMua) { this.heSoMua = heSoMua; }
    public int getMoTaToiThieu() { return moTaToiThieu; }
    public void setMoTaToiThieu(int moTaToiThieu) { this.moTaToiThieu = moTaToiThieu; }
    public double getHsNhoGiot() { return hsNhoGiot; }
    public void setHsNhoGiot(double hsNhoGiot) { this.hsNhoGiot = hsNhoGiot; }
    public double getHsPhunMua() { return hsPhunMua; }
    public void setHsPhunMua(double hsPhunMua) { this.hsPhunMua = hsPhunMua; }
    public double getHsRanh() { return hsRanh; }
    public void setHsRanh(double hsRanh) { this.hsRanh = hsRanh; }
    // CSDL cũ chưa có cột này thì mặc định 5 điểm %
    public double getMucLechThayThe() { return mucLechThayThe == null ? 5 : mucLechThayThe; }
    public void setMucLechThayThe(double mucLechThayThe) { this.mucLechThayThe = mucLechThayThe; }
    public LocalDateTime getLanLapGanNhat() { return lanLapGanNhat; }
    public void setLanLapGanNhat(LocalDateTime lanLapGanNhat) { this.lanLapGanNhat = lanLapGanNhat; }
}