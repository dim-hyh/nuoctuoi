package com.nuoctuoi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuoctuoi.model.ThuaRuong;

public interface ThuaRuongRepository extends JpaRepository<ThuaRuong, String> {
    List<ThuaRuong> findAllByOrderByMaThua();
}