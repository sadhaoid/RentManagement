package com.example.demo.Reposity;

import com.example.demo.Entity.TenantDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<TenantDo,Long> {
}
