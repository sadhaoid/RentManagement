package com.example.demo.controller;

import com.example.demo.entity.RoomDo;
import com.example.demo.entity.RoomWithTenantDto;
import com.example.demo.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;


    @PostMapping("/add")
    public void saveRoom(RoomDo room){
         roomService.addRoom(room);
    }

    @GetMapping("/{id}")
    public List<RoomWithTenantDto> getRoomWithTenant(@PathVariable Long id){
        return roomService.getRoomWithTenant(id);
    }
}
