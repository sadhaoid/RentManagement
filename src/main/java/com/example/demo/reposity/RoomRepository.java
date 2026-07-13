package com.example.demo.reposity;

import com.example.demo.entity.RoomDo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<RoomDo,Long> {
    @Query(value = "TRUNCATE TABLE room RESTART IDENTITY CASCADE",nativeQuery = true)
    @Modifying
    @Transactional
    void truncateRoom();

    List<RoomDo> findByApartmentId(Long apartmentId);
}
