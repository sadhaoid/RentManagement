package com.example.demo.utils;


import com.example.demo.entity.LeaseContractDo;
import com.example.demo.reposity.LeaseContractRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.collections4.ListUtils;

@RequiredArgsConstructor
public class ThreadPoolPractice {
    private final LeaseContractRepository leaseContractRepository;
    LocalDateTime validateDate = LocalDateTime.of(2024, 6, 1, 0, 0, 0);

    ConcurrentLinkedDeque<LeaseContractDo> concurrentCollection = new ConcurrentLinkedDeque<>();

    CountDownLatch countDownLatch = new CountDownLatch(4);


     void threadPool () throws InterruptedException {
        // 创建一个固定大小的线程池，线程池大小为4
        ExecutorService executor = Executors.newFixedThreadPool(4);

        List<List<LeaseContractDo>> lists = partitionContract();

//        executor.submit(() -> {
//            List<LeaseContractDo> list = lists.getFirst().stream().filter(leaseContractDo -> leaseContractDo.getEndDate().isAfter(validateDate)).toList();
//            concurrentCollection.addAll(list);
//            countDownLatch.countDown();



         for (int i = 0; i < lists.size(); i++) {
             int finalI = i;
             executor.submit(() -> {
                 lists.get(finalI).stream().filter(leaseContractDo -> leaseContractDo.getEndDate().isAfter(validateDate)).forEach(leaseContractDo -> concurrentCollection.add(leaseContractDo));
                 countDownLatch.countDown();
             });

         }

        // 关闭线程池，等待所有任务完成后再关闭
        countDownLatch.await();
        executor.shutdown();
    }

    public  List<List<LeaseContractDo>> partitionContract() {
        List<LeaseContractDo> contracts =  leaseContractRepository.findAll();

        return ListUtils.partition(contracts, 25000);

    }
}
