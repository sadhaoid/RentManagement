package com.example.demo.Entity;

import lombok.Data;

@Data
public class Apartment {
    String communityName;
    String address;
    String area;
    String city;
    String roomCount;
    String roomPrice;
    String roomType;
    String totalPrice;
    LandLord landLord;
}
