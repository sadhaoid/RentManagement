package com.example.demo.controller;

import com.example.demo.entity.ApartmentDo;
import com.example.demo.service.ApartmentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/apartment")
@RequiredArgsConstructor
public class ApartmentController {
    private final ApartmentService apartmentService;


    @PostMapping("/add")
    public void saveApartment(ApartmentDo apartment){
         apartmentService.addApartment(apartment);
    }
}
