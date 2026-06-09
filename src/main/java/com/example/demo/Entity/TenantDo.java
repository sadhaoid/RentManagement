package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tenant")
public class TenantDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String phone;
    String sex;
    String idCard;
    String emergencyContact;
    String emergencyPhone;
    //0注销/1正常
    Integer status;
    LocalDateTime createTime;
    LocalDateTime updateTime;
}
