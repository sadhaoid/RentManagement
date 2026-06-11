package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "room")
public class RoomDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long apartmentId;
    String roomNumber;
    Float roomArea;
    String roomType;
    Float roomPrice;
    //押金
    Float deposit;
    LocalDate createTime;
    LocalDate updateTime;
}
