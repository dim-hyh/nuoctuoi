package com.nuoctuoi.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuoctuoi.model.HanMucNuoc;

public interface HanMucNuocRepository extends JpaRepository<HanMucNuoc, String> {
    List<HanMucNuoc> findAllByOrderByMaHanMuc();
    List<HanMucNuoc> findByMaVungOrderByTuNgay(String maVung);
    // Kỳ hạn mức chứa ngày cho trước: tuNgay <= ngay <= denNgay
    Optional<HanMucNuoc> findFirstByMaVungAndTuNgayLessThanEqualAndDenNgayGreaterThanEqual(String maVung, LocalDate ngay1, LocalDate ngay2);
    // Kiểm tra trùng kỳ: tuNgay <= den và denNgay >= tu
    boolean existsByMaVungAndTuNgayLessThanEqualAndDenNgayGreaterThanEqual(String maVung, LocalDate den, LocalDate tu);
}