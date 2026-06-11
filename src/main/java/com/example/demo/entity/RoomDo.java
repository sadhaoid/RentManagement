package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "room")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long apartmentId;
    String roomNumber;
    Double area;
    //1主卧，2次卧，3单间
    Integer roomType;
    Double monthlyRent;
    //押金
    Double deposit;
    @Column(name = "created_at")
    LocalDateTime createTime;
    @Column(name = "updated_at")
    LocalDateTime updateTime;
}
