package com.nuoctuoi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuoctuoi.model.PhieuGhiDe;

public interface PhieuGhiDeRepository extends JpaRepository<PhieuGhiDe, String> {
    List<PhieuGhiDe> findAllByOrderByThoiDiemDesc();
    Optional<PhieuGhiDe> findFirstByMaDeXuatOrderByThoiDiemDesc(String maDeXuat);
    long countByTrangThai(String trangThai);
}