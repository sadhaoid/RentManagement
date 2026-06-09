package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "apartment")
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
    BigDecimal area;
    Integer roomCount;
    Integer hallCount;
    Integer toiletCount;
    //0整租，1合租
    Integer rentType;
    Integer status;
    @Column(name = "create_at")
    LocalDateTime createTime;
    @Column(name = "update_at")
    LocalDateTime updateTime;
}
