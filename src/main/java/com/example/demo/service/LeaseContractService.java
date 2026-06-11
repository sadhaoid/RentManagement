package com.example.demo.service;

import com.example.demo.reposity.LandLordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LeaseContractService {
    private LandLordRepository landLordRepository;

}
