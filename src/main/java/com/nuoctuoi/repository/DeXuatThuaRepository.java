package com.nuoctuoi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuoctuoi.model.DeXuatThua;

public interface DeXuatThuaRepository extends JpaRepository<DeXuatThua, Long> {
    List<DeXuatThua> findByMaDeXuat(String maDeXuat);
}