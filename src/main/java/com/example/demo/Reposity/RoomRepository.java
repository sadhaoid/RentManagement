package com.example.demo.Reposity;

import com.example.demo.Entity.RoomDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomDo,Long> {
}
