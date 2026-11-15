package com.nuoctuoi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuoctuoi.model.DeXuatTuoi;

public interface DeXuatTuoiRepository extends JpaRepository<DeXuatTuoi, String> {
    List<DeXuatTuoi> findAllByOrderByNgayLapDescMaDeXuatAsc();
    List<DeXuatTuoi> findByMaVungAndNgayLapBetweenOrderByNgayLap(String maVung, LocalDate tu, LocalDate den);
    long countByTrangThai(String trangThai);
}