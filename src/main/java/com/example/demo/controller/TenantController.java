package com.example.demo.controller;

import com.example.demo.entity.TenantDo;
import com.example.demo.service.TenantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tenant")
@AllArgsConstructor
public class TenantController {
    private final TenantService tenantService;


    @PostMapping("/add")
    public void saveTenant(TenantDo tenant){
         tenantService.addTenant(tenant);
    }
}
