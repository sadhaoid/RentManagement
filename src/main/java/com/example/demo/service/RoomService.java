package com.example.demo.service;

import com.example.demo.Entity.RoomDo;
import com.example.demo.Reposity.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public void addRoom(RoomDo roomDo){
        roomRepository.save(roomDo);
    }

    public void deleteRoom(Long id){
        roomRepository.deleteById(id);
    }

    public void updateRoom(RoomDo roomDo){
        roomRepository.save(roomDo);
    }

    public RoomDo getRoomById(Long id){
        return roomRepository.findById(id).orElse(null);
    }
}
