package com.nuoctuoi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuoctuoi.model.CayTrong;

public interface CayTrongRepository extends JpaRepository<CayTrong, String> {
    List<CayTrong> findAllByOrderByMaCay();
}