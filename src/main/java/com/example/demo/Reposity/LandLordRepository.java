package com.example.demo.Reposity;

import com.example.demo.Entity.LandLordDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandLordRepository extends JpaRepository<LandLordDo,Long> {
}
