package com.nuoctuoi.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.nuoctuoi.model.LichMuaVu;

// Dữ liệu một lịch mùa vụ để hiển thị (kèm ngày bắt đầu/kết thúc từng giai đoạn)
public class LichHienThi {

    public static class DongGiaiDoan {
        private final String ten;
        private final LocalDate tuNgay;
        private final LocalDate denNgay;
        private final int soNgay;

        public DongGiaiDoan(String ten, LocalDate tuNgay, LocalDate denNgay, int soNgay) {
            this.ten = ten;
            this.tuNgay = tuNgay;
            this.denNgay = denNgay;
            this.soNgay = soNgay;
        }

        public String getTen() { return ten; }
        public LocalDate getTuNgay() { return tuNgay; }
        public LocalDate getDenNgay() { return denNgay; }
        public int getSoNgay() { return soNgay; }
    }

    private LichMuaVu lich;
    private String tenCay;
    private LocalDate ngayThuHoach;
    private List<DongGiaiDoan> giaiDoans = new ArrayList<>();

    public LichMuaVu getLich() { return lich; }
    public void setLich(LichMuaVu lich) { this.lich = lich; }
    public String getTenCay() { return tenCay; }
    public void setTenCay(String tenCay) { this.tenCay = tenCay; }
    public LocalDate getNgayThuHoach() { return ngayThuHoach; }
    public void setNgayThuHoach(LocalDate ngayThuHoach) { this.ngayThuHoach = ngayThuHoach; }
    public List<DongGiaiDoan> getGiaiDoans() { return giaiDoans; }
}