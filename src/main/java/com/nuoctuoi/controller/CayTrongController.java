package com.nuoctuoi.controller;

import com.nuoctuoi.model.CayTrong;
import com.nuoctuoi.model.GiaiDoanSinhTruong;
import com.nuoctuoi.repository.CayTrongRepository;
import com.nuoctuoi.repository.GiaiDoanRepository;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// UC05 – Quản lý cây trồng và giai đoạn sinh trưởng (BM2)
@Controller
@RequestMapping("/cay-trong")
public class CayTrongController {

    private final CayTrongRepository cayRepo;
    private final GiaiDoanRepository giaiDoanRepo;

    public CayTrongController(CayTrongRepository cayRepo, GiaiDoanRepository giaiDoanRepo) {
        this.cayRepo = cayRepo;
        this.giaiDoanRepo = giaiDoanRepo;
    }

    @GetMapping
    public String danhSach(@RequestParam(name = "ma", required = false) String ma, Model model) {
        List<CayTrong> dsCay = cayRepo.findAllByOrderByMaCay();
        CayTrong cay = null;
        for (CayTrong c : dsCay) {
            if (c.getMaCay().equals(ma)) cay = c;
        }
        if (cay == null && !dsCay.isEmpty()) cay = dsCay.get(0);

        model.addAttribute("dsCay", dsCay);
        model.addAttribute("cay", cay);
        if (cay != null) {
            List<GiaiDoanSinhTruong> dsGd = giaiDoanRepo.findByMaCayOrderByThuTu(cay.getMaCay());
            int tong = 0;
            for (GiaiDoanSinhTruong g : dsGd) tong += g.getSoNgay();
            model.addAttribute("dsGiaiDoan", dsGd);
            model.addAttribute("tongNgay", tong);
        }
        return "cay-trong";
    }

    @PostMapping("/them-cay")
    public String themCay(@RequestParam("maCay") String maCay, @RequestParam("tenCay") String tenCay,
                          RedirectAttributes ra) {
        maCay = maCay.trim();
        if (maCay.isEmpty() || tenCay.isBlank()) {
            ra.addFlashAttribute("loi", "Phải nhập mã cây và tên cây.");
        } else if (cayRepo.existsById(maCay)) {
            ra.addFlashAttribute("loi", "Mã cây đã tồn tại.");
        } else {
            CayTrong c = new CayTrong();
            c.setMaCay(maCay);
            c.setTenCay(tenCay.trim());
            cayRepo.save(c);
            ra.addFlashAttribute("thongBao", "Đã thêm cây " + tenCay + ".");
        }
        return "redirect:/cay-trong?ma=" + maCay;
    }

    @PostMapping("/them-giai-doan")
    public String themGiaiDoan(@RequestParam("maCay") String maCay, @RequestParam("tenGiaiDoan") String tenGiaiDoan,
                               @RequestParam(name = "soNgay", required = false) Integer soNgay,
                               @RequestParam(name = "nguongDuoi", required = false) Double nguongDuoi,
                               @RequestParam(name = "nguongTren", required = false) Double nguongTren,
                               @RequestParam(name = "doSauRe", required = false) Double doSauRe,
                               RedirectAttributes ra) {
        if (tenGiaiDoan.isBlank()) {
            ra.addFlashAttribute("loi", "Chưa nhập tên giai đoạn.");
        } else if (soNgay == null || soNgay <= 0) {
            ra.addFlashAttribute("loi", "Số ngày phải lớn hơn 0.");
        } else if (nguongDuoi == null || nguongTren == null || nguongDuoi < 0 || nguongTren > 100
                || nguongDuoi >= nguongTren) {
            ra.addFlashAttribute("loi", "Ngưỡng phải trong 0–100% và ngưỡng dưới < ngưỡng trên (QĐ2).");
        } else if (doSauRe == null || doSauRe < 0.05 || doSauRe > 2) {
            ra.addFlashAttribute("loi", "Độ sâu rễ phải từ 0,05 đến 2,0 m (QĐ2).");
        } else {
            GiaiDoanSinhTruong g = new GiaiDoanSinhTruong();
            g.setMaCay(maCay);
            g.setThuTu((int) giaiDoanRepo.countByMaCay(maCay) + 1);
            g.setTenGiaiDoan(tenGiaiDoan.trim());
            g.setSoNgay(soNgay);
            g.setNguongDuoi(nguongDuoi);
            g.setNguongTren(nguongTren);
            g.setDoSauRe(doSauRe);
            giaiDoanRepo.save(g);
            ra.addFlashAttribute("thongBao", "Đã thêm giai đoạn " + tenGiaiDoan + ".");
        }
        return "redirect:/cay-trong?ma=" + maCay;
    }
}