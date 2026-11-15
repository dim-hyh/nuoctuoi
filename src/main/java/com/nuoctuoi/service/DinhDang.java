package com.nuoctuoi.service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.stereotype.Component;

// Hiển thị số và ngày kiểu Việt Nam. Dùng trong giao diện: ${@dd.so(x)}
@Component("dd")
public class DinhDang {

    private static final DateTimeFormatter NGAY = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter NGAY_GIO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public String so(Double so) {
        if (so == null) return "—";
        DecimalFormat f = new DecimalFormat("#,##0.#", DecimalFormatSymbols.getInstance(Locale.forLanguageTag("vi-VN")));
        return f.format(Math.round(so * 10) / 10.0);
    }

    public String ngay(LocalDate d) {
        return d == null ? "" : d.format(NGAY);
    }

    public String ngayGio(LocalDateTime d) {
        return d == null ? "" : d.format(NGAY_GIO);
    }

    public String tenVaiTro(String ma) {
        return com.nuoctuoi.model.HangSo.tenVaiTro(ma);
    }

    public static double lam1(double so) {
        return Math.round(so * 10) / 10.0;
    }
}