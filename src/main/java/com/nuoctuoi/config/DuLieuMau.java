package com.nuoctuoi.config;

import com.nuoctuoi.model.CayTrong;
import com.nuoctuoi.model.DuBaoMua;
import com.nuoctuoi.model.GiaiDoanSinhTruong;
import com.nuoctuoi.model.HangSo;
import com.nuoctuoi.model.HanMucNuoc;
import com.nuoctuoi.model.LichMuaVu;
import com.nuoctuoi.model.LichMuaVuThua;
import com.nuoctuoi.model.NguoiDung;
import com.nuoctuoi.model.QuyDinh;
import com.nuoctuoi.model.SoDoDoAm;
import com.nuoctuoi.model.ThuaRuong;
import com.nuoctuoi.model.VungTuoi;
import com.nuoctuoi.repository.CayTrongRepository;
import com.nuoctuoi.repository.DeXuatThuaRepository;
import com.nuoctuoi.repository.DeXuatTuoiRepository;
import com.nuoctuoi.repository.DuBaoMuaRepository;
import com.nuoctuoi.repository.GiaiDoanRepository;
import com.nuoctuoi.repository.HanMucNuocRepository;
import com.nuoctuoi.repository.LichMuaVuRepository;
import com.nuoctuoi.repository.LichMuaVuThuaRepository;
import com.nuoctuoi.repository.NguoiDungRepository;
import com.nuoctuoi.repository.PhieuGhiDeRepository;
import com.nuoctuoi.repository.QuyDinhRepository;
import com.nuoctuoi.repository.SoDoDoAmRepository;
import com.nuoctuoi.repository.ThuaRuongRepository;
import com.nuoctuoi.repository.VungTuoiRepository;
import com.nuoctuoi.service.MatKhau;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

// Tạo dữ liệu mẫu khi chạy lần đầu (thống nhất với báo cáo: ngày 06/10/2026, thửa T-014, T-015)
@Component
public class DuLieuMau implements CommandLineRunner {

    private final NguoiDungRepository nguoiDungRepo;
    private final VungTuoiRepository vungRepo;
    private final ThuaRuongRepository thuaRepo;
    private final CayTrongRepository cayRepo;
    private final GiaiDoanRepository giaiDoanRepo;
    private final LichMuaVuRepository lichRepo;
    private final LichMuaVuThuaRepository lichThuaRepo;
    private final SoDoDoAmRepository soDoRepo;
    private final DuBaoMuaRepository muaRepo;
    private final HanMucNuocRepository hanMucRepo;
    private final DeXuatTuoiRepository deXuatRepo;
    private final DeXuatThuaRepository deXuatThuaRepo;
    private final PhieuGhiDeRepository phieuRepo;
    private final QuyDinhRepository quyDinhRepo;

    public DuLieuMau(NguoiDungRepository nguoiDungRepo, VungTuoiRepository vungRepo, ThuaRuongRepository thuaRepo,
                     CayTrongRepository cayRepo, GiaiDoanRepository giaiDoanRepo, LichMuaVuRepository lichRepo,
                     LichMuaVuThuaRepository lichThuaRepo, SoDoDoAmRepository soDoRepo, DuBaoMuaRepository muaRepo,
                     HanMucNuocRepository hanMucRepo, DeXuatTuoiRepository deXuatRepo,
                     DeXuatThuaRepository deXuatThuaRepo, PhieuGhiDeRepository phieuRepo,
                     QuyDinhRepository quyDinhRepo) {
        this.nguoiDungRepo = nguoiDungRepo;
        this.vungRepo = vungRepo;
        this.thuaRepo = thuaRepo;
        this.cayRepo = cayRepo;
        this.giaiDoanRepo = giaiDoanRepo;
        this.lichRepo = lichRepo;
        this.lichThuaRepo = lichThuaRepo;
        this.soDoRepo = soDoRepo;
        this.muaRepo = muaRepo;
        this.hanMucRepo = hanMucRepo;
        this.deXuatRepo = deXuatRepo;
        this.deXuatThuaRepo = deXuatThuaRepo;
        this.phieuRepo = phieuRepo;
        this.quyDinhRepo = quyDinhRepo;
    }

    @Override
    public void run(String... args) {
        if (nguoiDungRepo.count() == 0) {
            tao();
        }
        // CSDL tạo từ bản cũ chưa có đủ tài khoản thì bổ sung
        if (!nguoiDungRepo.existsByTenDangNhap("admin")) {
            nguoiDung("admin", "Quản trị hệ thống", HangSo.QUAN_TRI);
        }
        if (!nguoiDungRepo.existsByTenDangNhap("hoa")) {
            nguoiDung("hoa", "Lê Thị Hoa", HangSo.CAN_BO);
        }
        if (!nguoiDungRepo.existsByTenDangNhap("tam")) {
            nguoiDung("tam", "Võ Minh Tâm", HangSo.BAN_QUAN_TRI);
        }
        // Mật khẩu cũ lưu kiểu SHA-256 -> đổi sang BCrypt (mật khẩu mặc định 123)
        for (NguoiDung nd : nguoiDungRepo.findAll()) {
            String hash = nd.getMatKhauHash();
            if (hash == null || !hash.startsWith("$2")) {
                nd.setMatKhauHash(MatKhau.bam("123"));
                nguoiDungRepo.save(nd);
            }
        }
    }

    // Xóa hết dữ liệu rồi tạo lại dữ liệu mẫu
    @Transactional
    public void khoiPhuc() {
        deXuatThuaRepo.deleteAllInBatch();
        deXuatRepo.deleteAllInBatch();
        phieuRepo.deleteAllInBatch();
        lichThuaRepo.deleteAllInBatch();
        lichRepo.deleteAllInBatch();
        giaiDoanRepo.deleteAllInBatch();
        cayRepo.deleteAllInBatch();
        soDoRepo.deleteAllInBatch();
        muaRepo.deleteAllInBatch();
        hanMucRepo.deleteAllInBatch();
        thuaRepo.deleteAllInBatch();
        vungRepo.deleteAllInBatch();
        nguoiDungRepo.deleteAllInBatch();
        quyDinhRepo.deleteAllInBatch();
        tao();
    }

    private void tao() {
        // Tài khoản demo – mật khẩu chỉ lưu dạng mã băm
        nguoiDung("hoa", "Lê Thị Hoa", HangSo.CAN_BO);
        nguoiDung("an", "Nguyễn Văn An", HangSo.NONG_HO);
        nguoiDung("binh", "Trần Văn Bình", HangSo.QUAN_LY);
        nguoiDung("tam", "Võ Minh Tâm", HangSo.BAN_QUAN_TRI);
        nguoiDung("admin", "Quản trị hệ thống", HangSo.QUAN_TRI);

        vung("V-01", "Vùng Bắc");
        vung("V-02", "Vùng Đông");

        thua("T-014", "V-02", "Nguyễn Văn An", 500, "Thịt pha cát", "Nhỏ giọt");
        thua("T-015", "V-02", "Nguyễn Văn An", 500, "Thịt pha cát", "Nhỏ giọt");
        thua("T-020", "V-01", "Phạm Thị Lan", 800, "Phù sa", "Phun mưa");

        CayTrong duaLeo = new CayTrong();
        duaLeo.setMaCay("C01");
        duaLeo.setTenCay("Dưa leo");
        cayRepo.save(duaLeo);
        giaiDoan("C01", 1, "Cây con", 10, 20, 28, 0.15);
        giaiDoan("C01", 2, "Phát triển", 15, 22, 30, 0.25);
        giaiDoan("C01", 3, "Ra hoa", 12, 25, 32, 0.3);
        giaiDoan("C01", 4, "Thu hoạch", 20, 22, 30, 0.3);

        LichMuaVu lich = new LichMuaVu();
        lich.setMaLich("L-01");
        lich.setMaCay("C01");
        lich.setNgayGieo(LocalDate.of(2026, 9, 1));
        lich.setDsThua("T-014, T-015");
        lichRepo.save(lich);
        lichThua("L-01", "T-014");
        lichThua("L-01", "T-015");

        soDo("T-014", LocalDateTime.of(2026, 10, 6, 4, 40), 22);
        soDo("T-015", LocalDateTime.of(2026, 10, 6, 4, 40), 22);

        DuBaoMua mua = new DuBaoMua();
        mua.setMaVung("V-02");
        mua.setNgay(LocalDate.of(2026, 10, 6));
        mua.setLuongMua(0);
        muaRepo.save(mua);

        // Đã dùng 1.458,7 m³; sau khi lập đề xuất 33,3 m³ (giữ chỗ) sẽ còn đúng 8 m³
        HanMucNuoc hm = new HanMucNuoc();
        hm.setMaHanMuc("HM-01");
        hm.setMaVung("V-02");
        hm.setTuNgay(LocalDate.of(2026, 10, 1));
        hm.setDenNgay(LocalDate.of(2026, 10, 15));
        hm.setHanMuc(1500);
        hm.setDaDung(1458.7);
        hanMucRepo.save(hm);

        QuyDinh qd = new QuyDinh();
        qd.setId(1L);
        qd.setGioLap(5);
        qd.setHanSoDoGio(6);
        qd.setHieuLucGio(24);
        qd.setCanhBaoPhanTram(20);
        qd.setHeSoMua(0.8);
        qd.setMoTaToiThieu(15);
        qd.setHsNhoGiot(0.9);
        qd.setHsPhunMua(0.75);
        qd.setHsRanh(0.6);
        qd.setMucLechThayThe(5);
        quyDinhRepo.save(qd);
    }

    private void nguoiDung(String tk, String hoTen, String vaiTro) {
        NguoiDung nd = new NguoiDung();
        nd.setTenDangNhap(tk);
        nd.setMatKhauHash(MatKhau.bam("123"));
        nd.setHoTen(hoTen);
        nd.setVaiTro(vaiTro);
        nguoiDungRepo.save(nd);
    }

    private void vung(String ma, String ten) {
        VungTuoi v = new VungTuoi();
        v.setMaVung(ma);
        v.setTenVung(ten);
        vungRepo.save(v);
    }

    private void thua(String ma, String vung, String chu, double dt, String dat, String hinhThuc) {
        ThuaRuong t = new ThuaRuong();
        t.setMaThua(ma);
        t.setMaVung(vung);
        t.setChuHo(chu);
        t.setDienTich(dt);
        t.setLoaiDat(dat);
        t.setHinhThucTuoi(hinhThuc);
        thuaRepo.save(t);
    }

    private void giaiDoan(String maCay, int thuTu, String ten, int soNgay, double duoi, double tren, double re) {
        GiaiDoanSinhTruong g = new GiaiDoanSinhTruong();
        g.setMaCay(maCay);
        g.setThuTu(thuTu);
        g.setTenGiaiDoan(ten);
        g.setSoNgay(soNgay);
        g.setNguongDuoi(duoi);
        g.setNguongTren(tren);
        g.setDoSauRe(re);
        giaiDoanRepo.save(g);
    }

    private void lichThua(String maLich, String maThua) {
        LichMuaVuThua lt = new LichMuaVuThua();
        lt.setMaLich(maLich);
        lt.setMaThua(maThua);
        lichThuaRepo.save(lt);
    }

    private void soDo(String maThua, LocalDateTime tg, double doAm) {
        SoDoDoAm s = new SoDoDoAm();
        s.setMaThua(maThua);
        s.setThoiDiem(tg);
        s.setDoAm(doAm);
        s.setNguoiGhi("Lê Thị Hoa");
        soDoRepo.save(s);
    }
}