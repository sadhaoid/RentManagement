package com.example.demo.utils;


import com.example.demo.entity.LeaseContractDo;
import com.example.demo.reposity.LeaseContractRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.apache.commons.collections4.ListUtils;

@RequiredArgsConstructor
public class ThreadPoolPractice {
    private final LeaseContractRepository leaseContractRepository;
    LocalDateTime validateDate = LocalDateTime.of(2024, 6, 1, 0, 0, 0);


     void threadPool (String[] args) {
        // 创建一个固定大小的线程池，线程池大小为4
        ExecutorService executor = Executors.newFixedThreadPool(4);

//        executor.submit(() -> {
//            System.out.println("Task 1 is running in thread: " + Thread.currentThread().getName());
//        });
//
//        executor.submit(() -> {
//            System.out.println("Task 2 is running in thread: " + Thread.currentThread().getName());
//        });
//
//        executor.submit(() -> {
//            System.out.println("Task 3 is running in thread: " + Thread.currentThread().getName());
//        });
//
//        executor.submit(() -> {
//            System.out.println("Task 4 is running in thread: " + Thread.currentThread().getName());
//        });
//
//        executor.submit(() -> {
//            System.out.println("Task 5 is running in thread: " + Thread.currentThread().getName());
//        });

        List<List<LeaseContractDo>> lists = partitionContract();

        executor.submit(() -> {
            lists.getFirst().stream().filter(leaseContractDo -> leaseContractDo.getEndDate().isAfter(validateDate)).toList();
        });

         executor.submit(() -> {
             lists.get(1).stream().filter(leaseContractDo -> leaseContractDo.getEndDate().isAfter(validateDate)).toList();
         });

         executor.submit(() -> {
             lists.get(2).stream().filter(leaseContractDo -> leaseContractDo.getEndDate().isAfter(validateDate)).toList();
         });

         executor.submit(() -> {
             lists.getLast().stream().filter(leaseContractDo -> leaseContractDo.getEndDate().isAfter(validateDate)).toList();
         });

        // 关闭线程池，等待所有任务完成后再关闭
        executor.shutdown();
    }

    public  List<List<LeaseContractDo>> partitionContract() {
        List<LeaseContractDo> contracts =  leaseContractRepository.findAll();

        return ListUtils.partition(contracts, 25000);

    }
}
