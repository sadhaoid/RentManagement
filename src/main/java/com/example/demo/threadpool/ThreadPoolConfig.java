package com.example.demo.threadpool;

import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolExecutor createThreadPool() {
       return new ThreadPoolExecutor(4, 12, 100, TimeUnit.SECONDS, new LinkedBlockingQueue<>(6), new ThreadPoolExecutor.CallerRunsPolicy() );
    }

    @PreDestroy
    public void shutdownThread() {
        createThreadPool().shutdown();
    }
}
