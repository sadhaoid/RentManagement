package com.example.demo.reposity;

import com.example.demo.entity.ApartmentDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ApartmentRepository extends JpaRepository<ApartmentDo,Long> {
}
