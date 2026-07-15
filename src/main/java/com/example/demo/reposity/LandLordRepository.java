package com.example.demo.reposity;

import com.example.demo.dto.LandLordDto;
import com.example.demo.entity.LandLordDo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LandLordRepository extends JpaRepository<LandLordDo,Long> {

    @Query(value = "TRUNCATE TABLE landlord RESTART IDENTITY CASCADE",nativeQuery = true)
    @Modifying
    @Transactional
    void truncateLandLord();

    @Query(""" 
SELECT new com.example.demo.dto.LandLordDto(
a.name,
COUNT(DISTINCT b.id),
COUNT(DISTINCT c.id),
COUNT(d.id)
)
from ApartmentDo b
         JOIN LandLordDo a ON b.landlordId = a.id
         JOIN RoomDo c ON b.id = c.apartmentId
         JOIN LeaseContractDo d ON c.id = d.roomId
WHERE d.status = 1
GROUP BY a.name
"""
    )
    List<LandLordDto> findLandLordStats();
}
