package com.example.demo.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryStats {
    String category;
    Double totalAmount;
    Integer totalQuantity;
    Integer totalOrders;
}
