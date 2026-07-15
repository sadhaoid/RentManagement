package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import com.example.demo.dto.LandLordDto;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "landlord")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LandLordDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String phone;
    //1=male, 2=female
    Integer sex;
    String idCard;
    String email;
    String bankAccount;
    //1启用，0禁用
    Integer status;
    @Column(name = "created_at")
    LocalDateTime createTime;
    @Column(name = "updated_at")
    LocalDateTime updateTime;
}
