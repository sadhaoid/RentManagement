package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "landlord")
public class LandLordDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String phone;
    Integer sex;
    String idCard;
    String email;
    String bankAccount;
    Integer status;
    LocalDate createTime;
    LocalDate updateTime;
}
