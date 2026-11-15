package com.nuoctuoi.config;

import com.nuoctuoi.dto.NguoiDungPhien;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

// Đưa dữ liệu dùng chung vào mọi trang
@ControllerAdvice
public class BienChung {

    private static final String[] THU = {"Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy", "Chủ Nhật"};

    // Người dùng đang đăng nhập (biến "nd" trong giao diện)
    @ModelAttribute("nd")
    public NguoiDungPhien nguoiDung(HttpSession session) {
        return (NguoiDungPhien) session.getAttribute("nd");
    }

    // Ngày hôm nay hiển thị trên đầu trang, ví dụ "Thứ Sáu, 13/11/2026"
    @ModelAttribute("homNay")
    public String homNay() {
        LocalDate d = LocalDate.now();
        return THU[d.getDayOfWeek().getValue() - 1] + ", " + d.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}