package com.example.demo.threadpool;

import com.example.demo.entity.LeaseContractDo;
import com.example.demo.reposity.LeaseContractRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.collections4.ListUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class ThreadPoolPracticeWithCompletableFuture {

    private final LeaseContractRepository leaseContractRepository;
    private final ThreadPoolExecutor threadPoolExecutor;

    LocalDateTime validateDate = LocalDateTime.of(2024, 6, 1, 0, 0, 0);

    ConcurrentLinkedDeque<LeaseContractDo> concurrentCollection = new ConcurrentLinkedDeque<>();

    public void threadPoolWithCompletableFuture() {

        List<List<LeaseContractDo>> lists = partitionContract();

        // 1. 每个分片对应一个 CompletableFuture，收集进一个 List
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = 0; i < lists.size(); i++) {
            int finalI = i;

            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    if (finalI == 0) {
                        int x = 1 / 0; // 故意制造异常，只让第一个分片失败，用于验证异常隔离效果
                    }

                    long startTime = System.currentTimeMillis();
                    lists.get(finalI).stream()
                            .filter(leaseContractDo -> leaseContractDo.getEndDate().isAfter(validateDate))
                            .forEach(leaseContractDo -> concurrentCollection.add(leaseContractDo));
                    long endTime = System.currentTimeMillis();
                    System.out.printf("线程%d耗时%dms%n", finalI, endTime - startTime);

                } catch (Exception e) {
                    // CompletableFuture 版本不需要手动 countDown，异常在这里被捕获、记录，
                    // 不会影响其他分片，也不会让整体卡死
                    log.error("分片 {} 处理失败：{}", finalI, e.getMessage(), e);
                }
            }, threadPoolExecutor); // 第二个参数必须传自己的线程池，否则会用默认的公共线程池

            futures.add(future);
        }

        CompletableFuture<Void> all = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        all.join();
    }

    public List<List<LeaseContractDo>> partitionContract() {
        List<LeaseContractDo> contracts = leaseContractRepository.findAll();
        return ListUtils.partition(contracts, 25000);
    }
}