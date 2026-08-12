package com.example.demo.controller;

import com.example.demo.utils.ThreadPoolPractice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/threadPools")
@RequiredArgsConstructor
public class ThreadPoolController {
    private final ThreadPoolPractice threadPoolPractice;

    @GetMapping("/init")
    public  void initThreadPool() throws Exception {
        threadPoolPractice.threadPool();
    }
}
