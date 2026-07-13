package com.example.demo.controller;

import com.example.demo.entity.ApartmentDo;
import com.example.demo.service.ApartmentService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apartment")
@RequiredArgsConstructor
public class ApartmentController {
    private final ApartmentService apartmentService;


    @PostMapping("/add")
    public void saveApartment(ApartmentDo apartment){
         apartmentService.addApartment(apartment);
    }

    @GetMapping("/get/{id}")
    public void getApartment(@PathVariable Long id){
        apartmentService.getApartmentById(id);
    }

    @GetMapping("/api/apartment/by-landlord/{landlordId}")
    public List<ApartmentDo> getLandlordApartments(@PathVariable Long landlordId){
        return apartmentService.getApartmentList(landlordId);
    }
}
