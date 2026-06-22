package com.example.demo.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class LandLordDto {
    Long id;
    String name;
    String phone;
    //1=male, 2=female
    Integer sex;
    String idCard;
    String email;
    String bankAccount;
    Integer status;
    LocalDateTime createTime;
    LocalDateTime updateTime;
}
