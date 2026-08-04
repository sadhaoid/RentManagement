package com.example.demo.order;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrderPractice {

    @PostConstruct
    public void OrderPractice() {
        List<Order> orders = new ArrayList<>(List.of(
                new Order("O1001", "U001", "FOOD", 300.00, "PAID", 3),
                new Order("O1002", "U002", "FOOD", 450.50, "PENDING", 2),
                new Order("O1003", "U003", "FOOD", 999.99, "REFUNDED", 5),
                new Order("O1004", "U001", "FOOD", 200.00, "PAID", 1),

                new Order("O2001", "U004", "BOOK", 1200.00, "PAID", 10),
                new Order("O2002", "U005", "BOOK", 800.00, "PAID", 5),
                new Order("O2003", "U006", "BOOK", 500.00, "REFUNDED", 3),
                new Order("O2004", "U004", "BOOK", 300.00, "PENDING", 2),

                new Order("O3001", "U007", "ELECTRONIC", 5000.00, "PAID", 2),
                new Order("O3002", "U008", "ELECTRONIC", 3000.00, "PENDING", 1),
                new Order("O3003", "U009", "ELECTRONIC", 2000.00, "REFUNDED", 1),
                new Order("O3004", "U007", "ELECTRONIC", 1500.00, "PAID", 3),

                new Order("O4001", "U010", "TOY", 100.00, "PAID", 1),
                new Order("O4002", "U011", "TOY", 200.00, "PENDING", 2),
                new Order("O4003", "U012", "TOY", 900.00, "REFUNDED", 5)
        ));


        List<Order> excludeRefunded = orders.stream().filter(order -> !(order.getStatus().equals("REFUNDED"))).toList();


        Map<String, List<Order>> collected = excludeRefunded.stream().collect(Collectors.groupingBy(Order::getCategory));

        List<CategoryStats> categoryStatsList = new ArrayList<>();
        for (Map.Entry<String, List<Order>> entry: collected.entrySet()){
            String category = entry.getKey();
            List<Order> orderList = entry.getValue();
            double sumAmount = orderList.stream().mapToDouble(Order::getAmount).sum();
            int sumQuantity = orderList.stream().mapToInt(Order::getQuantity).sum();
            int sumOrders = orderList.size();
            if (sumAmount >= 1000){
                categoryStatsList.add(CategoryStats
                        .builder()
                        .category(category)
                        .totalAmount(sumAmount)
                        .totalQuantity(sumQuantity)
                        .totalOrders(sumOrders)
                        .build());
            }

        }

        categoryStatsList.sort(Comparator.comparingDouble(CategoryStats::getTotalAmount).reversed());

        List<String> result = categoryStatsList
                .stream()
                .map(categoryStats
                        -> categoryStats.getCategory() + ":" + categoryStats.getTotalAmount() + ":" + categoryStats.getTotalQuantity()).toList();

        System.out.println(result);

    }
}
