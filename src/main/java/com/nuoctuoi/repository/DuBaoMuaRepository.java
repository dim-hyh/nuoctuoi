package com.nuoctuoi.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuoctuoi.model.DuBaoMua;

public interface DuBaoMuaRepository extends JpaRepository<DuBaoMua, Long> {
    List<DuBaoMua> findAllByOrderByNgayDesc();
    Optional<DuBaoMua> findFirstByMaVungAndNgay(String maVung, LocalDate ngay);
}