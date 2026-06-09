package com.example.demo.Reposity;

import com.example.demo.Entity.LeaseContractDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaseContractRepository extends JpaRepository<LeaseContractDo,Long> {
}
