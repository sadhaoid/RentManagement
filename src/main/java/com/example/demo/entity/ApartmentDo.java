package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "apartment")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long landlordId;
    String communityName;
    String address;
    String city;
    String district;
    Integer floor;
    Integer totalFloor;
    Double area;
    Integer roomCount;
    Integer hallCount;
    Integer toiletCount;
    //0整租，1合租
    Integer rentType;
    Integer status;
    @Column(name = "created_at")
    LocalDateTime createTime;
    @Column(name = "updated_at")
    LocalDateTime updateTime;
}
