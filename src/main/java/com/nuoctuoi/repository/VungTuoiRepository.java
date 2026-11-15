package com.nuoctuoi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuoctuoi.model.VungTuoi;

public interface VungTuoiRepository extends JpaRepository<VungTuoi, String> {
    List<VungTuoi> findAllByOrderByMaVung();
}