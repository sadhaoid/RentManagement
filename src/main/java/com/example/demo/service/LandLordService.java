package com.example.demo.service;

import com.example.demo.Entity.LandLordDo;
import com.example.demo.Reposity.LandLordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LandLordService {
    //房东CRUD
    private final LandLordRepository landLordRepository;

    public void addLandLord(LandLordDo landLordDo){
        landLordRepository.save(landLordDo);
    }

    public void deleteLandLord(Long id){
        landLordRepository.deleteById(id);
    }

    public void updateLandLord(LandLordDo landLordDo){
        landLordRepository.save(landLordDo);
    }

    public LandLordDo getLandLordById(Long id){
        return landLordRepository.findById(id).orElse(null);
    }

    public Map<Long,LandLordDo> getLandLordMap(){
        return landLordRepository.findAll().stream().collect(Collectors.toMap(LandLordDo::getId, landLord -> landLord));

    }
}
