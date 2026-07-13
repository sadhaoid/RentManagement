package com.example.demo.reposity;

import com.example.demo.entity.LeaseContractDo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeaseContractRepository extends JpaRepository<LeaseContractDo,Long> {
    @Query(value = "TRUNCATE TABLE lease_contract RESTART IDENTITY CASCADE",nativeQuery = true)
    @Modifying
    @Transactional
    void truncateLeaseContract();

     List<LeaseContractDo> findByRoomIdAndStatus(Long roomId, Integer status);
}
