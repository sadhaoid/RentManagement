package com.example.demo.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    String orderId;
    String userId;
    String Category;
    Double amount;
    String status;
    Integer quantity;

}
