package com.example.demo.utils;


import com.example.demo.entity.LeaseContractDo;
import com.example.demo.reposity.LeaseContractRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.*;

import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ThreadPoolPractice {
    private final LeaseContractRepository leaseContractRepository;
    private final ThreadPoolExecutor threadPoolExecutor;


    LocalDateTime validateDate = LocalDateTime.of(2024, 6, 1, 0, 0, 0);

    ConcurrentLinkedDeque<LeaseContractDo> concurrentCollection = new ConcurrentLinkedDeque<>();


     public void threadPool() throws InterruptedException {
        // 创建一个固定大小的线程池，线程池大小为4

        CountDownLatch countDownLatch = new CountDownLatch(partitionContract().size());


        List<List<LeaseContractDo>> lists = partitionContract();

         for (int i = 0; i < lists.size(); i++) {
//             int x = 1/0;
             int finalI = i;
             threadPoolExecutor.submit(() -> {
                 try {
                     if (finalI == 0) {
                         int x = 1 / 0;   // 现在挪进来了，只让第一个分片失败
                     }
                     Long startTime = System.currentTimeMillis();
                     lists.get(finalI).stream()
                             .filter(leaseContractDo -> leaseContractDo.getEndDate().isAfter(validateDate))
                             .forEach(leaseContractDo -> concurrentCollection.add(leaseContractDo));
                     Long endTime = System.currentTimeMillis();
                     System.out.printf("线程%d耗时%dms%n", finalI, endTime - startTime);
                 } catch (Exception e) {

                 } finally {
                     countDownLatch.countDown();

                 }

             });

         }

        countDownLatch.await();
    }

    public  List<List<LeaseContractDo>> partitionContract() {
        List<LeaseContractDo> contracts =  leaseContractRepository.findAll();

        return ListUtils.partition(contracts, 25000);

    }
}
