package com.example.demo.reposity;

import com.example.demo.entity.RoomDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomDo,Long> {
}
