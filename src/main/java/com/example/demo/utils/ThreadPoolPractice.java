package com.example.demo.utils;


import com.example.demo.entity.LeaseContractDo;
import com.example.demo.reposity.LeaseContractRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.*;

import org.apache.commons.collections4.ListUtils;

//    public static ExecutorService newFixedThreadPool(int nThreads) {
//        return new ThreadPoolExecutor(nThreads, nThreads,
//                                      0L, TimeUnit.MILLISECONDS,
//                                      new LinkedBlockingQueue<Runnable>());
//    }


//    public ThreadPoolExecutor(int corePoolSize,
//                              int maximumPoolSize,
//                              long keepAliveTime,
//                              TimeUnit unit,
//                              BlockingQueue<Runnable> workQueue) {
//        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
//             Executors.defaultThreadFactory(), defaultHandler);
//    }
@RequiredArgsConstructor
public class ThreadPoolPractice {
    private final LeaseContractRepository leaseContractRepository;
    LocalDateTime validateDate = LocalDateTime.of(2024, 6, 1, 0, 0, 0);

    ConcurrentLinkedDeque<LeaseContractDo> concurrentCollection = new ConcurrentLinkedDeque<>();

    CountDownLatch countDownLatch = new CountDownLatch(4);


     void threadPool () throws InterruptedException {
        // 创建一个固定大小的线程池，线程池大小为4
        ExecutorService executor = Executors.newFixedThreadPool(4);

//        new ThreadPoolExecutor(4, 12, 100000, TimeUnit.SECONDS, List<>(),)

        List<List<LeaseContractDo>> lists = partitionContract();

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
