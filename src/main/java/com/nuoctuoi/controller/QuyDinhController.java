package com.nuoctuoi.controller;

import com.nuoctuoi.config.DuLieuMau;
import com.nuoctuoi.model.QuyDinh;
import com.nuoctuoi.repository.QuyDinhRepository;
import com.nuoctuoi.service.XuLyDeXuat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// UC15 – Thay đổi quy định (Ban quản trị, Quản trị hệ thống)
@Controller
@RequestMapping("/quy-dinh")
public class QuyDinhController {

    private final QuyDinhRepository quyDinhRepo;
    private final XuLyDeXuat xuLyDeXuat;
    private final DuLieuMau duLieuMau;

    public QuyDinhController(QuyDinhRepository quyDinhRepo, XuLyDeXuat xuLyDeXuat, DuLieuMau duLieuMau) {
        this.quyDinhRepo = quyDinhRepo;
        this.xuLyDeXuat = xuLyDeXuat;
        this.duLieuMau = duLieuMau;
    }

    @GetMapping
    public String xem(Model model) {
        model.addAttribute("qd", xuLyDeXuat.layQuyDinh());
        return "quy-dinh";
    }

    @PostMapping("/luu")
    public String luu(@RequestParam("gioLap") int gioLap, @RequestParam("hanSoDoGio") int hanSoDoGio, @RequestParam("hieuLucGio") int hieuLucGio,
                      @RequestParam("canhBaoPhanTram") double canhBaoPhanTram, @RequestParam("heSoMua") double heSoMua,
                      @RequestParam("moTaToiThieu") int moTaToiThieu, @RequestParam("hsNhoGiot") double hsNhoGiot,
                      @RequestParam("hsPhunMua") double hsPhunMua, @RequestParam("hsRanh") double hsRanh,
                      @RequestParam("mucLechThayThe") double mucLechThayThe,
                      RedirectAttributes ra) {
        boolean hopLe = gioLap >= 0 && gioLap <= 23 && hanSoDoGio > 0 && hieuLucGio > 0
                && canhBaoPhanTram > 0 && canhBaoPhanTram < 100 && heSoMua > 0 && heSoMua <= 1
                && moTaToiThieu > 0 && hsNhoGiot > 0 && hsNhoGiot <= 1
                && hsPhunMua > 0 && hsPhunMua <= 1 && hsRanh > 0 && hsRanh <= 1
                && mucLechThayThe > 0 && mucLechThayThe < 100;
        if (!hopLe) {
            ra.addFlashAttribute("loi", "Giá trị quy định không hợp lệ.");
            return "redirect:/quy-dinh";
        }
        QuyDinh qd = xuLyDeXuat.layQuyDinh();
        qd.setGioLap(gioLap);
        qd.setHanSoDoGio(hanSoDoGio);
        qd.setHieuLucGio(hieuLucGio);
        qd.setCanhBaoPhanTram(canhBaoPhanTram);
        qd.setHeSoMua(heSoMua);
        qd.setMoTaToiThieu(moTaToiThieu);
        qd.setHsNhoGiot(hsNhoGiot);
        qd.setHsPhunMua(hsPhunMua);
        qd.setHsRanh(hsRanh);
        qd.setMucLechThayThe(mucLechThayThe);
        quyDinhRepo.save(qd);
        ra.addFlashAttribute("thongBao", "Đã lưu quy định. Áp dụng cho các lần tính sau.");
        return "redirect:/quy-dinh";
    }

    @PostMapping("/khoi-phuc")
    public String khoiPhuc(RedirectAttributes ra) {
        duLieuMau.khoiPhuc();
        ra.addFlashAttribute("thongBao", "Đã khôi phục dữ liệu mẫu.");
        return "redirect:/quy-dinh";
    }
}