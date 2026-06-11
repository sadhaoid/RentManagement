package com.example.demo.reposity;

import com.example.demo.entity.LeaseContractDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaseContractRepository extends JpaRepository<LeaseContractDo,Long> {
}
