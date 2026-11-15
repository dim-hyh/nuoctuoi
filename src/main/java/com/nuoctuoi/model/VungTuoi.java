package com.nuoctuoi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// Vùng tưới
@Entity
public class VungTuoi {

    @Id
    private String maVung;
    private String tenVung;

    public String getMaVung() { return maVung; }
    public void setMaVung(String maVung) { this.maVung = maVung; }
    public String getTenVung() { return tenVung; }
    public void setTenVung(String tenVung) { this.tenVung = tenVung; }
}