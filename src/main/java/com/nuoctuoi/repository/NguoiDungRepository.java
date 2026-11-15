package com.nuoctuoi.repository;

import com.nuoctuoi.model.NguoiDung;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {
    Optional<NguoiDung> findByTenDangNhap(String tenDangNhap);
    boolean existsByTenDangNhap(String tenDangNhap);
    List<NguoiDung> findAllByOrderByVaiTroAscTenDangNhapAsc();
}