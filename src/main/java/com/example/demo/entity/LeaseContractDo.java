package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "lease_contract")
public class LeaseContractDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String contractNumber;
    Long roomId;
    Long tenantId;
    LocalDate startDate;
    LocalDate endDate;
    Float monthlyRent;
    Float deposit;
    Integer payDay;
    Integer payCycle;
    //0待生效/1生效中/2已到期/3已退租
    Integer status;
    LocalDate signDate;
    String remark;
    LocalDateTime createTime;
    LocalDateTime updateTime;
}
