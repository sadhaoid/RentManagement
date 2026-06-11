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
@Table(name = "lease_contract")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaseContractDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long contractNo;
    Long roomId;
    Long tenantId;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Double monthlyRent;
    Double deposit;
    Integer payDay;
    Integer payCycle;
    //0待生效/1生效中/2已到期/3已退租
    Integer status;
    LocalDateTime signDate;
    String remark;
    @Column(name = "created_at")
    LocalDateTime createTime;
    @Column(name = "updated_at")
    LocalDateTime updateTime;
}
