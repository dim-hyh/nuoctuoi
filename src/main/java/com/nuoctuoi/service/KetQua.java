package com.nuoctuoi.service;

// Kết quả trả về từ lớp xử lý cho controller
public class KetQua {

    private final boolean thanhCong;
    private final String thongBao;

    private KetQua(boolean thanhCong, String thongBao) {
        this.thanhCong = thanhCong;
        this.thongBao = thongBao;
    }

    public static KetQua dung(String thongBao) { return new KetQua(true, thongBao); }
    public static KetQua sai(String thongBao) { return new KetQua(false, thongBao); }

    public boolean isThanhCong() { return thanhCong; }
    public String getThongBao() { return thongBao; }
}