package com.example.demo.reposity;

import com.example.demo.entity.TenantDo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TenantRepository extends JpaRepository<TenantDo,Long> {
    @Query(value = "TRUNCATE TABLE tenant RESTART IDENTITY CASCADE",nativeQuery = true)
    @Modifying
    @Transactional
    void truncateTenant();

    //todo 为什么必须是optional，为什么类型变成list会报错
//    Optional<TenantDo> findById(Long id);

    TenantDo findAllById(Long longs);
}
