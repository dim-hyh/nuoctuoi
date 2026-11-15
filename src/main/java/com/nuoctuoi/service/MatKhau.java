package com.nuoctuoi.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Mã hóa và kiểm tra mật khẩu bằng BCrypt, không lưu mật khẩu gốc
public final class MatKhau {

    private static final BCryptPasswordEncoder BCRYPT = new BCryptPasswordEncoder();

    private MatKhau() { }

    public static String bam(String matKhau) {
        return BCRYPT.encode(matKhau == null ? "" : matKhau);
    }

    public static boolean khop(String matKhau, String maBam) {
        return matKhau != null && maBam != null && BCRYPT.matches(matKhau, maBam);
    }
}