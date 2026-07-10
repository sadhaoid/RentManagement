package com.example.demo.controller;

import com.example.demo.entity.ApartmentDo;
import com.example.demo.entity.LeaseContractDo;
import com.example.demo.service.ApartmentService;
import com.example.demo.service.LeaseContractService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leaseContract")
@AllArgsConstructor
public class LeaseContractController {
    private final LeaseContractService leaseContractService;


    @PostMapping("/add")
    public void saveApartment( LeaseContractDo leaseContractDo){
        leaseContractService.addLeaseContract(leaseContractDo);
    }
}
