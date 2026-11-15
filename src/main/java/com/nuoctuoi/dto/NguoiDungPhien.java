package com.nuoctuoi.dto;

import com.nuoctuoi.model.HangSo;
import java.io.Serializable;

// Thông tin người dùng lưu trong phiên đăng nhập
public class NguoiDungPhien implements Serializable {

    private final String tenDangNhap;
    private final String hoTen;
    private final String vaiTro;

    public NguoiDungPhien(String tenDangNhap, String hoTen, String vaiTro) {
        this.tenDangNhap = tenDangNhap;
        this.hoTen = hoTen;
        this.vaiTro = vaiTro;
    }

    public String getTenDangNhap() { return tenDangNhap; }
    public String getHoTen() { return hoTen; }
    public String getVaiTro() { return vaiTro; }
    public String getTenVaiTro() { return HangSo.tenVaiTro(vaiTro); }

    public boolean isCanBo() { return HangSo.CAN_BO.equals(vaiTro); }
    public boolean isNongHo() { return HangSo.NONG_HO.equals(vaiTro); }
    public boolean isQuanLy() { return HangSo.QUAN_LY.equals(vaiTro); }
    public boolean isQuanTri() { return HangSo.QUAN_TRI.equals(vaiTro); }
    public boolean isBanQuanTri() { return HangSo.BAN_QUAN_TRI.equals(vaiTro); }
}