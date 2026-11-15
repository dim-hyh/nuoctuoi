package com.nuoctuoi.controller;

import com.nuoctuoi.dto.KyBaoCao;
import com.nuoctuoi.model.DeXuatTuoi;
import com.nuoctuoi.model.HanMucNuoc;
import com.nuoctuoi.model.QuyDinh;
import com.nuoctuoi.repository.DeXuatTuoiRepository;
import com.nuoctuoi.repository.HanMucNuocRepository;
import com.nuoctuoi.repository.PhieuGhiDeRepository;
import com.nuoctuoi.repository.VungTuoiRepository;
import com.nuoctuoi.service.XuLyDeXuat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// UC14 – Báo cáo sử dụng nước (BM8)
@Controller
public class BaoCaoController {

    private final VungTuoiRepository vungRepo;
    private final HanMucNuocRepository hanMucRepo;
    private final DeXuatTuoiRepository deXuatRepo;
    private final PhieuGhiDeRepository phieuRepo;
    private final XuLyDeXuat xuLyDeXuat;

    public BaoCaoController(VungTuoiRepository vungRepo, HanMucNuocRepository hanMucRepo,
                            DeXuatTuoiRepository deXuatRepo, PhieuGhiDeRepository phieuRepo,
                            XuLyDeXuat xuLyDeXuat) {
        this.vungRepo = vungRepo;
        this.hanMucRepo = hanMucRepo;
        this.deXuatRepo = deXuatRepo;
        this.phieuRepo = phieuRepo;
        this.xuLyDeXuat = xuLyDeXuat;
    }

    @GetMapping("/bao-cao")
    public String baoCao(@RequestParam(name = "vung", required = false, defaultValue = "V-02") String vung,
                         Model model) {
        QuyDinh qd = xuLyDeXuat.layQuyDinh();
        List<KyBaoCao> dsKy = new ArrayList<>();
        for (HanMucNuoc hm : hanMucRepo.findByMaVungOrderByTuNgay(vung)) {
            KyBaoCao ky = new KyBaoCao();
            ky.setHanMuc(hm);
            ky.setTrangThai(hm.trangThai(qd));
            for (DeXuatTuoi dx : deXuatRepo.findByMaVungAndNgayLapBetweenOrderByNgayLap(vung, hm.getTuNgay(), hm.getDenNgay())) {
                ky.them(dx, phieuRepo.findFirstByMaDeXuatOrderByThoiDiemDesc(dx.getMaDeXuat()).orElse(null));
            }
            dsKy.add(ky);
        }
        model.addAttribute("vung", vung);
        model.addAttribute("dsVung", vungRepo.findAllByOrderByMaVung());
        model.addAttribute("dsKy", dsKy);
        return "bao-cao";
    }
}