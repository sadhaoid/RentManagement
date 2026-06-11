package com.example.demo.service;

import com.example.demo.entity.ApartmentDo;
import com.example.demo.reposity.ApartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;
    //公寓CRUD
    public void addApartment(ApartmentDo apartmentDo){
        apartmentRepository.save(apartmentDo);
    }

    public void deleteApartment(Long id){
        apartmentRepository.deleteById(id);
    }

    public void updateApartment(ApartmentDo apartmentDo){
        apartmentRepository.save(apartmentDo);
    }

    public ApartmentDo getApartmentById(Long id){
        return apartmentRepository.findById(id).orElse(null);
    }
}
