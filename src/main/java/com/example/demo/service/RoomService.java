package com.example.demo.service;

import com.example.demo.entity.RoomDo;
import com.example.demo.reposity.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

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

    public void truncateRoom(){
        roomRepository.truncateRoom();
    }

    public Map<Long, RoomDo> getRoomMap() {
        return roomRepository.findAll().stream().collect(Collectors.toMap(RoomDo::getId, room -> room));
    }
}
