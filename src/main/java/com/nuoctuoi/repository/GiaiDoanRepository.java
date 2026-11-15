package com.nuoctuoi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuoctuoi.model.GiaiDoanSinhTruong;

public interface GiaiDoanRepository extends JpaRepository<GiaiDoanSinhTruong, Long> {
    List<GiaiDoanSinhTruong> findByMaCayOrderByThuTu(String maCay);
    long countByMaCay(String maCay);
}