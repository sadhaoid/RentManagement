package com.example.demo.reposity;

import com.example.demo.entity.LandLordDo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LandLordRepository extends JpaRepository<LandLordDo,Long> {

    @Query(value = "TRUNCATE TABLE landlord RESTART IDENTITY CASCADE",nativeQuery = true)
    @Modifying
    @Transactional
    void truncateLandLord();
}
