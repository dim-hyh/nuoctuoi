package com.nuoctuoi.dto;

import java.util.ArrayList;
import java.util.List;

import com.nuoctuoi.model.DeXuatTuoi;
import com.nuoctuoi.model.HanMucNuoc;
import com.nuoctuoi.model.PhieuGhiDe;

// Một kỳ hạn mức trong báo cáo sử dụng nước (BM8)
public class KyBaoCao {

    public static class Dong {
        private final DeXuatTuoi deXuat;
        private final PhieuGhiDe phieu;

        public Dong(DeXuatTuoi deXuat, PhieuGhiDe phieu) {
            this.deXuat = deXuat;
            this.phieu = phieu;
        }

        public DeXuatTuoi getDeXuat() { return deXuat; }
        public PhieuGhiDe getPhieu() { return phieu; }

        public Double getChenhLech() {
            if (deXuat.getLuongThucTe() == null) return null;
            return deXuat.getLuongThucTe() - deXuat.getLuongGoc();
        }

        public String getGhiDe() {
            return phieu == null ? "" : phieu.getLyDo() + " (" + phieu.getTrangThai() + ")";
        }
    }

    private HanMucNuoc hanMuc;
    private String trangThai;
    private List<Dong> dong = new ArrayList<>();
    private double tongGoc;
    private double tongApDung;
    private double tongThucTe;

    public void them(DeXuatTuoi dx, PhieuGhiDe phieu) {
        dong.add(new Dong(dx, phieu));
        tongGoc += dx.getLuongGoc();
        tongApDung += dx.getLuongApDung();
        if (dx.getLuongThucTe() != null) tongThucTe += dx.getLuongThucTe();
    }

    public HanMucNuoc getHanMuc() { return hanMuc; }
    public void setHanMuc(HanMucNuoc hanMuc) { this.hanMuc = hanMuc; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
    public List<Dong> getDong() { return dong; }
    public double getTongGoc() { return tongGoc; }
    public double getTongApDung() { return tongApDung; }
    public double getTongThucTe() { return tongThucTe; }
    public double getTongChenhLech() { return tongThucTe - tongGoc; }
}