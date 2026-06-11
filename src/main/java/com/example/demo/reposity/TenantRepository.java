package com.example.demo.reposity;

import com.example.demo.entity.TenantDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<TenantDo,Long> {
}
