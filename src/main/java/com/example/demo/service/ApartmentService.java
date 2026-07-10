package com.example.demo.service;

import com.example.demo.entity.ApartmentDo;
import com.example.demo.exception.BusinessExceptionDemo;
import com.example.demo.reposity.ApartmentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
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
        return apartmentRepository.findById(id).orElseThrow(() -> new BusinessExceptionDemo("Apartment not found with id: " + id));
    }

    public void truncateApartment(){
        apartmentRepository.truncateApartment();
    }

    public Map<Long, ApartmentDo> getApartmentMap() {
        return apartmentRepository.findAll().stream().collect(Collectors.toMap(ApartmentDo::getId, apartment -> apartment));
    }
}
