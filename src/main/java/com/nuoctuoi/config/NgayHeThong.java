package com.nuoctuoi.config;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// Ngày hiện tại của hệ thống.
// Chạy demo với dữ liệu mẫu tháng 10 thì đặt nuoctuoi.ngay-he-thong=2026-10-06 trong application.properties;
// để trống (hoặc xóa dòng đó) thì lấy ngày thật của máy.
@Component
public class NgayHeThong {

    private static LocalDate ngayCoDinh;

    public NgayHeThong(@Value("${nuoctuoi.ngay-he-thong:}") String ngay) {
        ngayCoDinh = ngay.isBlank() ? null : LocalDate.parse(ngay.trim());
    }

    public static LocalDate homNay() {
        return ngayCoDinh != null ? ngayCoDinh : LocalDate.now();
    }
}