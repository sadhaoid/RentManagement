package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tenant")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TenantDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String phone;
    Integer sex;
    String idCard;
    String emergencyContact;
    String emergencyPhone;
    //0注销/1正常
    Integer status;
    @Column(name = "created_at")
    LocalDateTime createTime;
    @Column(name = "updated_at")
    LocalDateTime updateTime;
}
