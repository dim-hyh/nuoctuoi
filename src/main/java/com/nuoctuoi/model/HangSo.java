package com.nuoctuoi.model;

// Các hằng số dùng chung: vai trò, trạng thái, danh mục chọn
public final class HangSo {

    private HangSo() { }

    // Vai trò
    public static final String CAN_BO = "CanBoKyThuat";
    public static final String NONG_HO = "NongHo";
    public static final String QUAN_LY = "QuanLyTo";
    public static final String QUAN_TRI = "QuanTriHeThong";
    public static final String BAN_QUAN_TRI = "BanQuanTri";

    // Trạng thái đề xuất tưới (theo sơ đồ trạng thái)
    public static final String DX_KHONG_CAN_TUOI = "Không cần tưới";
    public static final String DX_CHO_XAC_NHAN = "Chờ xác nhận";
    public static final String DX_DA_XAC_NHAN = "Đã xác nhận";
    public static final String DX_CHO_DUYET = "Chờ duyệt";
    public static final String DX_DA_GHI_DE = "Đã ghi đè";
    public static final String DX_DA_THUC_HIEN = "Đã thực hiện";
    public static final String DX_HET_HIEU_LUC = "Hết hiệu lực";
    public static final String DX_BI_THAY_THE = "Bị thay thế";

    // Trạng thái phiếu ghi đè
    public static final String PGD_DA_AP_DUNG = "Đã áp dụng";
    public static final String PGD_CHO_DUYET = "Chờ duyệt";
    public static final String PGD_DA_DUYET = "Đã duyệt";
    public static final String PGD_DIEU_CHINH = "Điều chỉnh";
    public static final String PGD_TU_CHOI = "Từ chối";

    public static final String[] DS_VAI_TRO = { CAN_BO, NONG_HO, QUAN_LY, BAN_QUAN_TRI, QUAN_TRI };

    public static final String[] HINH_THUC_TUOI = { "Nhỏ giọt", "Phun mưa", "Rãnh" };

    public static final String[] LY_DO_GHI_DE = {
        "Nắng nóng bất thường", "Cây có dấu hiệu héo", "Máy bơm hỏng", "Đất giữ nước kém", "Lý do khác"
    };

    public static String tenVaiTro(String ma) {
        if (CAN_BO.equals(ma)) return "Cán bộ kỹ thuật";
        if (NONG_HO.equals(ma)) return "Nông hộ";
        if (QUAN_LY.equals(ma)) return "Quản lý tổ thủy nông";
        if (BAN_QUAN_TRI.equals(ma)) return "Ban quản trị";
        if (QUAN_TRI.equals(ma)) return "Quản trị hệ thống";
        return "";
    }
}