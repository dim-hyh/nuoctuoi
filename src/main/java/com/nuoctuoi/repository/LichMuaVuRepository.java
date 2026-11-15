package com.nuoctuoi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuoctuoi.model.LichMuaVu;

public interface LichMuaVuRepository extends JpaRepository<LichMuaVu, String> {
    List<LichMuaVu> findAllByOrderByMaLich();
}