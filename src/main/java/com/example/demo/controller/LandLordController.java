package com.example.demo.controller;

import com.example.demo.entity.ApartmentDo;
import com.example.demo.entity.LandLordDo;
import com.example.demo.service.ApartmentService;
import com.example.demo.service.LandLordService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/landlord")
@AllArgsConstructor
public class LandLordController {
    private final LandLordService landLordService;

    @PostMapping("/add")
    public void saveLandlord(LandLordDo landLordDo){
        landLordService.addLandLord(landLordDo);
    }
}
