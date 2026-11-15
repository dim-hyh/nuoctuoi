package com.nuoctuoi.repository;

import com.nuoctuoi.model.LichMuaVuThua;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LichMuaVuThuaRepository extends JpaRepository<LichMuaVuThua, Long> {

    List<LichMuaVuThua> findByMaLichOrderByMaThua(String maLich);
    boolean existsByMaThua(String maThua);
    List<LichMuaVuThua> findByMaThua(String maThua);

}