package com.nuoctuoi.service;

import com.nuoctuoi.model.DeXuatTuoi;
import com.nuoctuoi.model.HangSo;
import com.nuoctuoi.model.HanMucNuoc;
import com.nuoctuoi.model.PhieuGhiDe;
import com.nuoctuoi.model.QuyDinh;
import com.nuoctuoi.repository.DeXuatTuoiRepository;
import com.nuoctuoi.repository.PhieuGhiDeRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Lớp điều khiển nghiệp vụ ghi đè đề xuất và phê duyệt ghi đè
@Service
public class XuLyGhiDe {

    private final DeXuatTuoiRepository deXuatRepo;
    private final PhieuGhiDeRepository phieuRepo;
    private final XuLyDeXuat xuLyDeXuat;

    public XuLyGhiDe(DeXuatTuoiRepository deXuatRepo, PhieuGhiDeRepository phieuRepo, XuLyDeXuat xuLyDeXuat) {
        this.deXuatRepo = deXuatRepo;
        this.phieuRepo = phieuRepo;
        this.xuLyDeXuat = xuLyDeXuat;
    }

    private double conLaiCuaVung(DeXuatTuoi dx) {
        HanMucNuoc hm = xuLyDeXuat.timHanMuc(dx.getMaVung(), dx.getNgayLap());
        return hm != null ? hm.getConLai() : 0;
    }

    // QĐ8: ghi đè phải có lý do, mô tả đủ dài, lượng mới ≥ 0
    @Transactional
    public KetQua ghiDe(String maDeXuat, String lyDo, String moTa, Double luongMoi, String nguoiGhiDe) {
        QuyDinh qd = xuLyDeXuat.layQuyDinh();
        DeXuatTuoi dx = deXuatRepo.findById(maDeXuat).orElse(null);
        if (dx == null || !HangSo.DX_CHO_XAC_NHAN.equals(dx.getTrangThai()))
            return KetQua.sai("Chỉ ghi đè được đề xuất đang chờ xác nhận.");
        if (lyDo == null || lyDo.isBlank())
            return KetQua.sai("Phải chọn lý do ghi đè (QĐ8).");
        moTa = moTa == null ? "" : moTa.trim();
        if (moTa.length() < qd.getMoTaToiThieu())
            return KetQua.sai("Mô tả lý do phải có ít nhất " + qd.getMoTaToiThieu() + " ký tự (QĐ8).");
        if (luongMoi == null || luongMoi < 0)
            return KetQua.sai("Lượng mới phải lớn hơn hoặc bằng 0 (QĐ8).");

        double conLai = conLaiCuaVung(dx);
        double tang = luongMoi - dx.getLuongGoc();
        boolean vuotHanMuc = tang > conLai + 0.001;

        PhieuGhiDe phieu = new PhieuGhiDe();
        phieu.setMaPhieu(String.format("PGĐ-%03d", phieuRepo.count() + 1));
        phieu.setMaDeXuat(dx.getMaDeXuat());
        phieu.setNguoiGhiDe(nguoiGhiDe);
        phieu.setThoiDiem(LocalDateTime.now());
        phieu.setLyDo(lyDo);
        phieu.setMoTa(moTa);
        phieu.setLuongGoc(dx.getLuongGoc());
        phieu.setLuongMoi(DinhDang.lam1(luongMoi));
        phieu.setTrangThai(vuotHanMuc ? HangSo.PGD_CHO_DUYET : HangSo.PGD_DA_AP_DUNG);
        phieu.setYKien("");
        phieu.setNguoiDuyet("");
        phieuRepo.save(phieu);

        if (vuotHanMuc) {
            dx.setTrangThai(HangSo.DX_CHO_DUYET);
        } else {
            dx.setLuongApDung(phieu.getLuongMoi());
            dx.setTrangThai(HangSo.DX_DA_GHI_DE);
        }
        deXuatRepo.save(dx);

        if (vuotHanMuc)
            return KetQua.dung("Lượng tăng " + DinhDang.lam1(tang) + " m³ vượt phần còn lại " + DinhDang.lam1(conLai)
                    + " m³ → phiếu " + phieu.getMaPhieu() + " chờ quản lý tổ duyệt.");
        return KetQua.dung("Đã ghi đè, áp dụng ngay vì không vượt hạn mức.");
    }

    // QĐ9: lượng tối đa được duyệt = lượng gốc + phần hạn mức còn lại
    public double luongToiDa(PhieuGhiDe phieu) {
        DeXuatTuoi dx = deXuatRepo.findById(phieu.getMaDeXuat()).orElseThrow();
        return DinhDang.lam1(phieu.getLuongGoc() + conLaiCuaVung(dx));
    }

    // QĐ9: chấp nhận / điều chỉnh / từ chối
    @Transactional
    public KetQua duyet(String maPhieu, String quyetDinh, Double luongDuyet, String yKien, String nguoiDuyet) {
        PhieuGhiDe phieu = phieuRepo.findById(maPhieu).orElse(null);
        if (phieu == null || !HangSo.PGD_CHO_DUYET.equals(phieu.getTrangThai()))
            return KetQua.sai("Phiếu không ở trạng thái chờ duyệt.");
        DeXuatTuoi dx = deXuatRepo.findById(phieu.getMaDeXuat()).orElseThrow();
        yKien = yKien == null ? "" : yKien.trim();

        if (("Điều chỉnh".equals(quyetDinh) || "Từ chối".equals(quyetDinh)) && yKien.isEmpty())
            return KetQua.sai("Điều chỉnh hoặc từ chối thì phải ghi ý kiến (QĐ9).");

        if ("Từ chối".equals(quyetDinh)) {
            phieu.setTrangThai(HangSo.PGD_TU_CHOI);
            phieu.setLuongDuyet(phieu.getLuongGoc());
            dx.setLuongApDung(dx.getLuongGoc());
            dx.setTrangThai(HangSo.DX_CHO_XAC_NHAN);           // quay về đề xuất gốc
        } else {
            double luong = "Chấp nhận".equals(quyetDinh) ? phieu.getLuongMoi()
                    : (luongDuyet == null ? -1 : luongDuyet);
            double toiDa = luongToiDa(phieu);
            if (luong < 0)
                return KetQua.sai("Lượng duyệt phải lớn hơn hoặc bằng 0.");
            if (luong > toiDa + 0.001)
                return KetQua.sai("Lượng duyệt không được vượt " + toiDa + " m³ (gốc + còn lại, QĐ9).");

            phieu.setTrangThai("Chấp nhận".equals(quyetDinh) ? HangSo.PGD_DA_DUYET : HangSo.PGD_DIEU_CHINH);
            phieu.setLuongDuyet(DinhDang.lam1(luong));
            dx.setLuongApDung(phieu.getLuongDuyet());
            dx.setTrangThai(HangSo.DX_DA_GHI_DE);
        }

        phieu.setYKien(yKien);
        phieu.setNguoiDuyet(nguoiDuyet);
        phieuRepo.save(phieu);
        deXuatRepo.save(dx);
        return KetQua.dung("Đã lưu quyết định cho phiếu " + maPhieu + ".");
    }
}