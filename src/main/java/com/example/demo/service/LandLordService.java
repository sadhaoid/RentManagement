package com.example.demo.service;

import com.example.demo.dto.LandLordDto;
import com.example.demo.entity.LandLordDo;
import com.example.demo.reposity.LandLordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<LandLordDto> summaryLandLordDetails() {
       return landLordRepository.findLandLordStats();
    }

    public void truncateLandLord(){
        landLordRepository.truncateLandLord();
    }
}
