package com.example.demo.Reposity;

import com.example.demo.Entity.ApartmentDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ApartmentRepository extends JpaRepository<ApartmentDo,Long> {
}
