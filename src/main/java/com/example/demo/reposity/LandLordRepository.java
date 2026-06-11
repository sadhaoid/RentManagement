package com.example.demo.reposity;

import com.example.demo.entity.LandLordDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandLordRepository extends JpaRepository<LandLordDo,Long> {
}
