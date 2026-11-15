package com.nuoctuoi.controller;

import com.nuoctuoi.dto.LichHienThi;
import com.nuoctuoi.model.CayTrong;
import com.nuoctuoi.model.GiaiDoanSinhTruong;
import com.nuoctuoi.model.LichMuaVu;
import com.nuoctuoi.model.LichMuaVuThua;
import com.nuoctuoi.repository.CayTrongRepository;
import com.nuoctuoi.repository.GiaiDoanRepository;
import com.nuoctuoi.repository.LichMuaVuRepository;
import com.nuoctuoi.repository.LichMuaVuThuaRepository;
import com.nuoctuoi.repository.ThuaRuongRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// UC06 – Lập lịch mùa vụ (BM3)
@Controller
@RequestMapping("/lich-mua-vu")
public class LichMuaVuController {

    private final LichMuaVuRepository lichRepo;
    private final LichMuaVuThuaRepository lichThuaRepo;
    private final CayTrongRepository cayRepo;
    private final GiaiDoanRepository giaiDoanRepo;
    private final ThuaRuongRepository thuaRepo;

    public LichMuaVuController(LichMuaVuRepository lichRepo, LichMuaVuThuaRepository lichThuaRepo,
                               CayTrongRepository cayRepo, GiaiDoanRepository giaiDoanRepo,
                               ThuaRuongRepository thuaRepo) {
        this.lichRepo = lichRepo;
        this.lichThuaRepo = lichThuaRepo;
        this.cayRepo = cayRepo;
        this.giaiDoanRepo = giaiDoanRepo;
        this.thuaRepo = thuaRepo;
    }

    // Ngày thu hoạch xong của một lịch = ngày gieo + tổng số ngày các giai đoạn - 1
    private LocalDate ngayKetThuc(LichMuaVu lich) {
        long tong = 0;
        for (GiaiDoanSinhTruong g : giaiDoanRepo.findByMaCayOrderByThuTu(lich.getMaCay())) {
            tong += g.getSoNgay();
        }
        return lich.getNgayGieo().plusDays(Math.max(tong, 1) - 1);
    }

    @GetMapping
    public String danhSach(Model model) {
        List<LichHienThi> dsLich = new ArrayList<>();
        for (LichMuaVu lich : lichRepo.findAllByOrderByMaLich()) {
            LichHienThi ht = new LichHienThi();
            ht.setLich(lich);
            CayTrong cay = cayRepo.findById(lich.getMaCay()).orElse(null);
            ht.setTenCay(cay != null ? cay.getTenCay() : lich.getMaCay());

            // Tính ngày bắt đầu, kết thúc của từng giai đoạn
            LocalDate tu = lich.getNgayGieo();
            for (GiaiDoanSinhTruong g : giaiDoanRepo.findByMaCayOrderByThuTu(lich.getMaCay())) {
                LocalDate den = tu.plusDays(g.getSoNgay() - 1);
                ht.getGiaiDoans().add(new LichHienThi.DongGiaiDoan(g.getTenGiaiDoan(), tu, den, g.getSoNgay()));
                tu = den.plusDays(1);
            }
            ht.setNgayThuHoach(tu.minusDays(1));
            dsLich.add(ht);
        }
        model.addAttribute("dsLich", dsLich);
        model.addAttribute("dsCay", cayRepo.findAllByOrderByMaCay());
        model.addAttribute("dsThua", thuaRepo.findAllByOrderByMaThua());
        return "lich-mua-vu";
    }

    @PostMapping("/them")
    public String them(@RequestParam("maCay") String maCay,
                       @RequestParam(name = "ngayGieo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate ngayGieo,
                       @RequestParam(name = "thuas", required = false) List<String> thuas,
                       RedirectAttributes ra) {
        if (ngayGieo == null) {
            ra.addFlashAttribute("loi", "Chưa chọn ngày gieo.");
            return "redirect:/lich-mua-vu";
        }
        if (thuas == null || thuas.isEmpty()) {
            ra.addFlashAttribute("loi", "Phải chọn ít nhất 1 thửa.");
            return "redirect:/lich-mua-vu";
        }
        // QĐ3: mỗi thửa chỉ có một lịch mùa vụ đang áp dụng.
        // Vụ cũ đã thu hoạch xong (trước ngày gieo mới) thì được trồng vụ mới.
        for (String t : thuas) {
            for (LichMuaVuThua lt : lichThuaRepo.findByMaThua(t)) {
                LichMuaVu cu = lichRepo.findById(lt.getMaLich()).orElse(null);
                if (cu == null) continue;
                LocalDate ketThuc = ngayKetThuc(cu);
                if (!ketThuc.isBefore(ngayGieo)) {
                    ra.addFlashAttribute("loi", "Thửa " + t + " đang thuộc lịch " + cu.getMaLich()
                            + " (đến " + ketThuc.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                            + "). Ngày gieo mới phải sau ngày này (QĐ3).");
                    return "redirect:/lich-mua-vu";
                }
            }
        }

        String ma = String.format("L-%02d", lichRepo.count() + 1);
        LichMuaVu lich = new LichMuaVu();
        lich.setMaLich(ma);
        lich.setMaCay(maCay);
        lich.setNgayGieo(ngayGieo);
        lich.setDsThua(String.join(", ", thuas));
        lichRepo.save(lich);
        for (String t : thuas) {
            LichMuaVuThua lt = new LichMuaVuThua();
            lt.setMaLich(ma);
            lt.setMaThua(t);
            lichThuaRepo.save(lt);
        }
        ra.addFlashAttribute("thongBao", "Đã lập lịch " + ma + ".");
        return "redirect:/lich-mua-vu";
    }
}