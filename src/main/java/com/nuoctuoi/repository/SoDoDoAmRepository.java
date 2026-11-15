package com.nuoctuoi.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuoctuoi.model.SoDoDoAm;

public interface SoDoDoAmRepository extends JpaRepository<SoDoDoAm, Long> {
    List<SoDoDoAm> findAllByOrderByThoiDiemDesc();
    // Số đo mới nhất của thửa trong khoảng thời gian (QĐ4)
    Optional<SoDoDoAm> findFirstByMaThuaAndThoiDiemBetweenOrderByThoiDiemDesc(String maThua, LocalDateTime tu, LocalDateTime den);
}