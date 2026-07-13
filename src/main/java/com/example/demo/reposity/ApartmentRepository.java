package com.example.demo.reposity;

import com.example.demo.entity.ApartmentDo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface  ApartmentRepository extends JpaRepository<ApartmentDo,Long> {

    @Query(value = "TRUNCATE TABLE apartment RESTART IDENTITY CASCADE",nativeQuery = true)
    @Modifying
    @Transactional
    void truncateApartment();

    List<ApartmentDo> findByLandlordId(Long landlordId);

}
