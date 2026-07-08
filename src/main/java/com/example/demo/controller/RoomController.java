package com.example.demo.controller;

import com.example.demo.entity.RoomDo;
import com.example.demo.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/room")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;


    @PostMapping("/add")
    public void saveRoom(RoomDo room){
         roomService.addRoom(room);
    }
}
