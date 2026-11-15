package com.nuoctuoi.service;

import com.nuoctuoi.model.DeXuatThua;
import com.nuoctuoi.model.DeXuatTuoi;
import com.nuoctuoi.model.DuBaoMua;
import com.nuoctuoi.model.GiaiDoanSinhTruong;
import com.nuoctuoi.model.HangSo;
import com.nuoctuoi.model.HanMucNuoc;
import com.nuoctuoi.model.LichMuaVu;
import com.nuoctuoi.model.LichMuaVuThua;
import com.nuoctuoi.model.QuyDinh;
import com.nuoctuoi.model.SoDoDoAm;
import com.nuoctuoi.model.ThuaRuong;
import com.nuoctuoi.repository.DeXuatThuaRepository;
import com.nuoctuoi.repository.DeXuatTuoiRepository;
import com.nuoctuoi.repository.DuBaoMuaRepository;
import com.nuoctuoi.repository.GiaiDoanRepository;
import com.nuoctuoi.repository.HanMucNuocRepository;
import com.nuoctuoi.repository.LichMuaVuRepository;
import com.nuoctuoi.repository.LichMuaVuThuaRepository;
import com.nuoctuoi.repository.QuyDinhRepository;
import com.nuoctuoi.repository.SoDoDoAmRepository;
import com.nuoctuoi.repository.ThuaRuongRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Lớp điều khiển nghiệp vụ lập đề xuất tưới, xác nhận và ghi lượng thực tế
@Service
public class XuLyDeXuat {

    private final QuyDinhRepository quyDinhRepo;
    private final LichMuaVuRepository lichRepo;
    private final LichMuaVuThuaRepository lichThuaRepo;
    private final GiaiDoanRepository giaiDoanRepo;
    private final ThuaRuongRepository thuaRepo;
    private final SoDoDoAmRepository soDoRepo;
    private final DuBaoMuaRepository muaRepo;
    private final HanMucNuocRepository hanMucRepo;
    private final DeXuatTuoiRepository deXuatRepo;
    private final DeXuatThuaRepository deXuatThuaRepo;

    public XuLyDeXuat(QuyDinhRepository quyDinhRepo, LichMuaVuRepository lichRepo,
                      LichMuaVuThuaRepository lichThuaRepo, GiaiDoanRepository giaiDoanRepo,
                      ThuaRuongRepository thuaRepo, SoDoDoAmRepository soDoRepo,
                      DuBaoMuaRepository muaRepo, HanMucNuocRepository hanMucRepo,
                      DeXuatTuoiRepository deXuatRepo, DeXuatThuaRepository deXuatThuaRepo) {
        this.quyDinhRepo = quyDinhRepo;
        this.lichRepo = lichRepo;
        this.lichThuaRepo = lichThuaRepo;
        this.giaiDoanRepo = giaiDoanRepo;
        this.thuaRepo = thuaRepo;
        this.soDoRepo = soDoRepo;
        this.muaRepo = muaRepo;
        this.hanMucRepo = hanMucRepo;
        this.deXuatRepo = deXuatRepo;
        this.deXuatThuaRepo = deXuatThuaRepo;
    }

    public QuyDinh layQuyDinh() {
        return quyDinhRepo.findById(1L).orElseThrow();
    }

    public HanMucNuoc timHanMuc(String maVung, LocalDate ngay) {
        return hanMucRepo.findFirstByMaVungAndTuNgayLessThanEqualAndDenNgayGreaterThanEqual(maVung, ngay, ngay)
                .orElse(null);
    }

    // QĐ6: V = (ngưỡng trên − ẩm)/100 × độ sâu rễ × diện tích / hiệu suất − mưa hữu hiệu
    public static double tinhLuongNuoc(double nguongTren, double doAm, double doSauRe,
                                       double dienTich, double hieuSuat, double muaMm, double heSoMua) {
        double v = (nguongTren - doAm) / 100 * doSauRe * dienTich / hieuSuat;
        double muaHuuHieu = heSoMua * muaMm / 1000 * dienTich;   // mm → m³
        v = v - muaHuuHieu;
        return v > 0 ? v : 0;
    }

    // Cây đang ở giai đoạn nào vào ngày cho trước (null nếu ngoài mùa vụ)
    public GiaiDoanSinhTruong giaiDoanTai(LichMuaVu lich, LocalDate ngay) {
        long soNgay = ChronoUnit.DAYS.between(lich.getNgayGieo(), ngay);
        if (soNgay < 0) return null;
        long cong = 0;
        for (GiaiDoanSinhTruong gd : giaiDoanRepo.findByMaCayOrderByThuTu(lich.getMaCay())) {
            cong += gd.getSoNgay();
            if (soNgay < cong) return gd;
        }
        return null;
    }

    // Đề xuất còn hiệu lực theo sơ đồ trạng thái: Chờ xác nhận, Đã xác nhận
    private static boolean conHieuLuc(String tt) {
        return HangSo.DX_CHO_XAC_NHAN.equals(tt) || HangSo.DX_DA_XAC_NHAN.equals(tt);
    }

    // Trả lại phần nước đã giữ chỗ trong hạn mức khi đề xuất không còn dùng
    private void traGiuCho(DeXuatTuoi dx) {
        if (dx.getLuongGiuCho() <= 0) return;
        HanMucNuoc hm = timHanMuc(dx.getMaVung(), dx.getNgayLap());
        if (hm != null) {
            hm.setDaDung(DinhDang.lam1(hm.getDaDung() - dx.getLuongGiuCho()));
            hanMucRepo.save(hm);
        }
        dx.setLuongGiuCho(0);
    }

    // QĐ6: quá thời hạn hiệu lực (24 giờ) mà chưa thực hiện thì chuyển "Hết hiệu lực"
    @Transactional
    public int capNhatHetHieuLuc(LocalDateTime moc) {
        int dem = 0;
        for (DeXuatTuoi dx : deXuatRepo.findAll()) {
            boolean xet = conHieuLuc(dx.getTrangThai()) || HangSo.DX_KHONG_CAN_TUOI.equals(dx.getTrangThai());
            if (xet && dx.getHieuLucDen() != null && !dx.getHieuLucDen().isAfter(moc)) {
                traGiuCho(dx);
                dx.setTrangThai(HangSo.DX_HET_HIEU_LUC);
                deXuatRepo.save(dx);
                dem++;
            }
        }
        return dem;
    }

    // Mã đề xuất: ĐX-yyyy-MMdd-<số thửa đầu>; đề xuất lập lại sau khi bị thay thế thêm đuôi -2, -3...
    private String maMoi(String goc) {
        if (!deXuatRepo.existsById(goc)) return goc;
        int k = 2;
        while (deXuatRepo.existsById(goc + "-" + k)) k++;
        return goc + "-" + k;
    }

    private String maGoc(LichMuaVu lich, LocalDate ngay) {
        String thuaDau = lichThuaRepo.findByMaLichOrderByMaThua(lich.getMaLich()).get(0).getMaThua();
        return "ĐX-" + ngay.format(DateTimeFormatter.ofPattern("yyyy-MMdd")) + "-" + thuaDau.substring(2);
    }

    // Đã có đề xuất (chưa bị thay thế) của lịch này trong ngày chưa
    private boolean daCoDeXuat(String goc) {
        if (!deXuatRepo.existsById(goc)) return false;
        String ma = goc;
        int k = 2;
        while (true) {
            DeXuatTuoi dx = deXuatRepo.findById(ma).orElse(null);
            if (dx == null) return false;
            if (!HangSo.DX_BI_THAY_THE.equals(dx.getTrangThai())) return true;
            ma = goc + "-" + k++;
        }
    }

    // Lập đề xuất cho tất cả lịch mùa vụ đang hoạt động (bộ hẹn giờ gọi lúc 05:00)
    @Transactional
    public List<String> lapDeXuat(LocalDate ngay) {
        List<String> thongBao = new ArrayList<>();
        QuyDinh qd = layQuyDinh();
        LocalDateTime moc = ngay.atStartOfDay().plusHours(qd.getGioLap());

        // Ghi lại mốc của lần chạy gần nhất (dùng để đánh dấu số đo Hợp lệ / Quá hạn)
        if (qd.getLanLapGanNhat() == null || moc.isAfter(qd.getLanLapGanNhat())) {
            qd.setLanLapGanNhat(moc);
            quyDinhRepo.save(qd);
        }

        int het = capNhatHetHieuLuc(moc);
        if (het > 0) thongBao.add("Có " + het + " đề xuất cũ quá " + qd.getHieuLucGio() + " giờ chuyển sang Hết hiệu lực.");

        boolean coLich = false;
        for (LichMuaVu lich : lichRepo.findAllByOrderByMaLich()) {
            if (giaiDoanTai(lich, ngay) == null) continue;                      // ngoài mùa vụ
            if (lichThuaRepo.findByMaLichOrderByMaThua(lich.getMaLich()).isEmpty()) continue;
            coLich = true;
            String goc = maGoc(lich, ngay);
            if (daCoDeXuat(goc)) {
                thongBao.add(goc + " đã được lập trước đó.");
                continue;
            }
            lapChoLich(lich, ngay, moc, maMoi(goc), thongBao);
        }
        if (!coLich) thongBao.add("Không có lịch mùa vụ nào đang hoạt động vào ngày này.");
        return thongBao;
    }

    // Tính và lưu đề xuất cho một lịch mùa vụ; moc là thời điểm lập (05:00 hoặc lúc có số đo mới)
    private DeXuatTuoi lapChoLich(LichMuaVu lich, LocalDate ngay, LocalDateTime moc, String maDx, List<String> thongBao) {
        QuyDinh qd = layQuyDinh();
        GiaiDoanSinhTruong gd = giaiDoanTai(lich, ngay);
        if (gd == null) return null;
        List<LichMuaVuThua> dsThua = lichThuaRepo.findByMaLichOrderByMaThua(lich.getMaLich());
        ThuaRuong thuaDau = thuaRepo.findById(dsThua.get(0).getMaThua()).orElseThrow();

        DuBaoMua mua = muaRepo.findFirstByMaVungAndNgay(thuaDau.getMaVung(), ngay).orElse(null);
        double muaMm = mua != null ? mua.getLuongMua() : 0;

        double tong = 0;
        List<String> apDung = new ArrayList<>();
        List<String> canCu = new ArrayList<>();
        List<String> canCuSo = new ArrayList<>();
        DinhDang dd = new DinhDang();

        for (LichMuaVuThua lt : dsThua) {
            ThuaRuong thua = thuaRepo.findById(lt.getMaThua()).orElseThrow();
            // QĐ4: chỉ dùng số đo trong 6 giờ trước thời điểm lập
            SoDoDoAm soDo = soDoRepo.findFirstByMaThuaAndThoiDiemBetweenOrderByThoiDiemDesc(
                    thua.getMaThua(), moc.minusHours(qd.getHanSoDoGio()), moc).orElse(null);
            if (soDo == null) {
                thongBao.add(thua.getMaThua() + ": không có số đo trong " + qd.getHanSoDoGio() + " giờ (QĐ4).");
                continue;
            }
            canCu.add(thua.getMaThua() + " " + dd.so(soDo.getDoAm()) + "% lúc "
                    + soDo.getThoiDiem().format(DateTimeFormatter.ofPattern("HH:mm")));
            canCuSo.add(thua.getMaThua() + "=" + soDo.getDoAm());

            if (soDo.getDoAm() >= gd.getNguongDuoi()) continue;   // đất còn đủ ẩm

            double v = tinhLuongNuoc(gd.getNguongTren(), soDo.getDoAm(), gd.getDoSauRe(),
                    thua.getDienTich(), thua.hieuSuat(qd), muaMm, qd.getHeSoMua());
            if (v > 0) {
                tong += v;
                apDung.add(thua.getMaThua());
            }
        }
        if (canCu.isEmpty()) return null;

        tong = DinhDang.lam1(tong);

        // Giữ chỗ lượng đề xuất trong hạn mức của vùng
        HanMucNuoc hm = timHanMuc(thuaDau.getMaVung(), ngay);
        double giuCho = (hm != null && tong > 0) ? tong : 0;
        if (giuCho > 0) {
            hm.setDaDung(DinhDang.lam1(hm.getDaDung() + giuCho));
            hanMucRepo.save(hm);
        }

        List<String> phamVi = new ArrayList<>();
        if (apDung.isEmpty()) {
            for (LichMuaVuThua lt : dsThua) phamVi.add(lt.getMaThua());
        } else {
            phamVi.addAll(apDung);
        }

        DeXuatTuoi dx = new DeXuatTuoi();
        dx.setMaDeXuat(maDx);
        dx.setMaLich(lich.getMaLich());
        dx.setMaVung(thuaDau.getMaVung());
        dx.setNgayLap(ngay);
        dx.setGiaiDoan(gd.getTenGiaiDoan());
        dx.setLuongGoc(tong);
        dx.setLuongApDung(tong);
        dx.setKhungGio(tong > 0 ? "05:00–08:00" : "—");
        dx.setHieuLucDen(moc.plusHours(qd.getHieuLucGio()));
        dx.setCanCu(String.join("; ", canCu) + (muaMm > 0 ? "; mưa " + dd.so(muaMm) + " mm" : ""));
        dx.setCanCuSo(String.join(";", canCuSo));
        dx.setDsThua(String.join(", ", phamVi));
        dx.setTrangThai(tong > 0 ? HangSo.DX_CHO_XAC_NHAN : HangSo.DX_KHONG_CAN_TUOI);
        dx.setLuongGiuCho(giuCho);
        deXuatRepo.save(dx);

        for (String maThua : phamVi) {
            DeXuatThua dt = new DeXuatThua();
            dt.setMaDeXuat(maDx);
            dt.setMaThua(maThua);
            deXuatThuaRepo.save(dt);
        }
        thongBao.add("Đã lập " + maDx + ": " + dd.so(tong) + " m³ (" + dx.getTrangThai() + ").");
        return dx;
    }

    // Độ ẩm căn cứ của một thửa trong đề xuất (null nếu thửa không có trong căn cứ)
    private static Double doAmCanCu(DeXuatTuoi dx, String maThua) {
        if (dx.getCanCuSo() == null) return null;
        for (String phan : dx.getCanCuSo().split(";")) {
            String[] kv = phan.split("=");
            if (kv.length == 2 && kv[0].equals(maThua)) return Double.valueOf(kv[1]);
        }
        return null;
    }

    // QĐ6: có số đo mới lệch quá mức quy định (5 điểm %) so với số đo căn cứ
    // thì đề xuất còn hiệu lực chuyển "Bị thay thế" và hệ thống lập đề xuất mới
    @Transactional
    public List<String> kiemTraThayThe(String maThua, double doAmMoi, LocalDateTime thoiDiem) {
        List<String> thongBao = new ArrayList<>();
        QuyDinh qd = layQuyDinh();
        for (DeXuatTuoi dx : deXuatRepo.findAll()) {
            if (!conHieuLuc(dx.getTrangThai())) continue;
            LocalDateTime mocLap = dx.getNgayLap().atStartOfDay().plusHours(qd.getGioLap());
            if (!thoiDiem.isAfter(mocLap) || thoiDiem.isAfter(dx.getHieuLucDen())) continue;   // ngoài thời gian hiệu lực
            Double cu = doAmCanCu(dx, maThua);
            if (cu == null || Math.abs(doAmMoi - cu) <= qd.getMucLechThayThe()) continue;

            traGiuCho(dx);
            dx.setTrangThai(HangSo.DX_BI_THAY_THE);
            deXuatRepo.save(dx);
            thongBao.add("Số đo mới của " + maThua + " lệch " + new DinhDang().so(Math.abs(doAmMoi - cu))
                    + " điểm % so với căn cứ: " + dx.getMaDeXuat() + " chuyển Bị thay thế.");

            LichMuaVu lich = lichRepo.findById(dx.getMaLich()).orElse(null);
            if (lich != null) {
                lapChoLich(lich, dx.getNgayLap(), thoiDiem, maMoi(maGoc(lich, dx.getNgayLap())), thongBao);
            }
        }
        return thongBao;
    }

    // Mốc 05:00 của lần lập đề xuất gần nhất (dùng để đánh dấu số đo Hợp lệ / Quá hạn)
    public LocalDateTime mocLapGanNhat() {
        return layQuyDinh().getLanLapGanNhat();
    }

    @Transactional
    public KetQua xacNhan(String maDeXuat) {
        DeXuatTuoi dx = deXuatRepo.findById(maDeXuat).orElse(null);
        if (dx == null) return KetQua.sai("Không tìm thấy đề xuất.");
        if (HangSo.DX_HET_HIEU_LUC.equals(dx.getTrangThai()) || HangSo.DX_BI_THAY_THE.equals(dx.getTrangThai()))
            return KetQua.sai("Đề xuất đã hết hiệu lực hoặc bị thay thế, không thể xác nhận.");
        if (!HangSo.DX_CHO_XAC_NHAN.equals(dx.getTrangThai()))
            return KetQua.sai("Chỉ xác nhận được đề xuất đang chờ xác nhận.");
        dx.setTrangThai(HangSo.DX_DA_XAC_NHAN);
        deXuatRepo.save(dx);
        return KetQua.dung("Đã xác nhận đề xuất " + maDeXuat + ".");
    }

    // QĐ7: ghi lượng tưới thực tế, trả phần giữ chỗ và trừ lượng thực tế vào hạn mức
    @Transactional
    public KetQua ghiThucTe(String maDeXuat, Double luong) {
        DeXuatTuoi dx = deXuatRepo.findById(maDeXuat).orElse(null);
        if (dx == null) return KetQua.sai("Không tìm thấy đề xuất.");
        if (!HangSo.DX_DA_XAC_NHAN.equals(dx.getTrangThai()) && !HangSo.DX_DA_GHI_DE.equals(dx.getTrangThai()))
            return KetQua.sai("Chỉ ghi thực tế cho đề xuất đã xác nhận hoặc đã ghi đè.");
        if (luong == null || luong <= 0)
            return KetQua.sai("Lượng thực tế phải lớn hơn 0 (QĐ7).");

        dx.setLuongThucTe(DinhDang.lam1(luong));
        dx.setTrangThai(HangSo.DX_DA_THUC_HIEN);

        HanMucNuoc hm = timHanMuc(dx.getMaVung(), dx.getNgayLap());
        if (hm != null) {
            hm.setDaDung(DinhDang.lam1(hm.getDaDung() - dx.getLuongGiuCho() + luong));
            hanMucRepo.save(hm);
        }
        dx.setLuongGiuCho(0);
        deXuatRepo.save(dx);
        return KetQua.dung("Đã ghi lượng thực tế " + dx.getLuongThucTe() + " m³ cho " + maDeXuat + ".");
    }
}