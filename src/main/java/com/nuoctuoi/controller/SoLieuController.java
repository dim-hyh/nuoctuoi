package com.nuoctuoi.controller;

import com.nuoctuoi.dto.NguoiDungPhien;
import com.nuoctuoi.model.DuBaoMua;
import com.nuoctuoi.model.SoDoDoAm;
import com.nuoctuoi.repository.DeXuatTuoiRepository;
import com.nuoctuoi.repository.DuBaoMuaRepository;
import com.nuoctuoi.repository.SoDoDoAmRepository;
import com.nuoctuoi.repository.ThuaRuongRepository;
import com.nuoctuoi.repository.VungTuoiRepository;
import com.nuoctuoi.service.XuLyDeXuat;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// UC07 – Ghi nhận số liệu đồng ruộng (BM4)
@Controller
@RequestMapping("/so-lieu")
public class SoLieuController {

    private final SoDoDoAmRepository soDoRepo;
    private final DuBaoMuaRepository muaRepo;
    private final ThuaRuongRepository thuaRepo;
    private final VungTuoiRepository vungRepo;
    private final DeXuatTuoiRepository deXuatRepo;
    private final XuLyDeXuat xuLyDeXuat;

    public SoLieuController(SoDoDoAmRepository soDoRepo, DuBaoMuaRepository muaRepo,
                            ThuaRuongRepository thuaRepo, VungTuoiRepository vungRepo,
                            DeXuatTuoiRepository deXuatRepo, XuLyDeXuat xuLyDeXuat) {
        this.deXuatRepo = deXuatRepo;
        this.xuLyDeXuat = xuLyDeXuat;
        this.soDoRepo = soDoRepo;
        this.muaRepo = muaRepo;
        this.thuaRepo = thuaRepo;
        this.vungRepo = vungRepo;
    }

    @GetMapping
    public String danhSach(Model model) {
        List<SoDoDoAm> dsSoDo = soDoRepo.findAllByOrderByThoiDiemDesc();
        model.addAttribute("dsSoDo", dsSoDo);

        // QĐ4: số đo cũ hơn 6 giờ so với mốc lập đề xuất gần nhất là "Quá hạn"
        LocalDateTime moc = xuLyDeXuat.mocLapGanNhat();
        int han = xuLyDeXuat.layQuyDinh().getHanSoDoGio();
        Map<Long, String> trangThai = new HashMap<>();
        for (SoDoDoAm s : dsSoDo) {
            boolean quaHan = moc != null && s.getThoiDiem().isBefore(moc.minusHours(han));
            trangThai.put(s.getId(), quaHan ? "Quá hạn" : "Hợp lệ");
        }
        model.addAttribute("ttSoDo", trangThai);
        model.addAttribute("dsMua", muaRepo.findAllByOrderByNgayDesc());
        model.addAttribute("dsThua", thuaRepo.findAllByOrderByMaThua());
        model.addAttribute("dsVung", vungRepo.findAllByOrderByMaVung());
        // Điền sẵn: ngày cần lập đề xuất tiếp theo, giờ đo 04:40 sáng (trong 6 giờ trước 05:00 – QĐ4)
        LocalDate ngay = DeXuatController.ngayTiepTheo(deXuatRepo);
        model.addAttribute("ngayMacDinh", ngay.toString());
        model.addAttribute("thoiDiemMacDinh", ngay + "T04:40");
        return "so-lieu";
    }

    @PostMapping("/them-so-do")
    public String themSoDo(@RequestParam("maThua") String maThua,
                           @RequestParam(name = "thoiDiem", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime thoiDiem,
                           @RequestParam(name = "doAm", required = false) Double doAm,
                           HttpSession session, RedirectAttributes ra) {
        if (thoiDiem == null) {
            ra.addFlashAttribute("loi", "Chưa nhập thời điểm đo.");
        } else if (doAm == null || doAm < 0 || doAm > 100) {
            ra.addFlashAttribute("loi", "Độ ẩm phải trong khoảng 0–100% (QĐ4).");
        } else {
            NguoiDungPhien nd = (NguoiDungPhien) session.getAttribute("nd");
            SoDoDoAm s = new SoDoDoAm();
            s.setMaThua(maThua);
            s.setThoiDiem(thoiDiem);
            s.setDoAm(doAm);
            s.setNguoiGhi(nd.getHoTen());
            soDoRepo.save(s);
            // QĐ6: số đo mới lệch nhiều so với căn cứ thì thay đề xuất cũ
            List<String> thayThe = xuLyDeXuat.kiemTraThayThe(maThua, doAm, thoiDiem);
            ra.addFlashAttribute("thongBao", "Đã lưu số đo của thửa " + maThua + ". " + String.join(" ", thayThe));
        }
        return "redirect:/so-lieu";
    }

    @PostMapping("/them-mua")
    public String themMua(@RequestParam("maVung") String maVung,
                          @RequestParam(name = "ngay", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate ngay,
                          @RequestParam(name = "luongMua", required = false) Double luongMua,
                          RedirectAttributes ra) {
        if (ngay == null || luongMua == null || luongMua < 0) {
            ra.addFlashAttribute("loi", "Dự báo mưa không hợp lệ.");
            return "redirect:/so-lieu";
        }
        DuBaoMua m = muaRepo.findFirstByMaVungAndNgay(maVung, ngay).orElse(new DuBaoMua());
        m.setMaVung(maVung);
        m.setNgay(ngay);
        m.setLuongMua(luongMua);
        muaRepo.save(m);
        ra.addFlashAttribute("thongBao", "Đã lưu dự báo mưa.");
        return "redirect:/so-lieu";
    }
}