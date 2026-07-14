package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoomWithTenantDto {
    Long roomId;
    Long apartmentId;
    Double area;
    Double monthlyRent;
    Double deposit;
    String name;
    String phone;
    //todo 租客可能不存在用上什么类型去表达“有/没有”
}
