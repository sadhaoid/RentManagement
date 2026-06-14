package com.example.demo.service;

import com.example.demo.entity.TenantDo;
import com.example.demo.reposity.TenantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TenantService {
    //租客CRUD

    private final TenantRepository tenantRepository;

    public void addTenant(TenantDo tenantDo){
        tenantRepository.save(tenantDo);
    }

    public void deleteTenant(Long id){
        tenantRepository.deleteById(id);
    }

    public void updateTenant(TenantDo tenantDo){
        tenantRepository.save(tenantDo);
    }

    public TenantDo getTenantById(Long id){
        return tenantRepository.findById(id).orElse(null);
    }

    public void truncateTenant(){
        tenantRepository.truncateTenant();
    }

    public Map<Long, TenantDo> getTenantMap() {
        return tenantRepository.findAll().stream().collect(Collectors.toMap(TenantDo::getId, tenant -> tenant));
    }
}
