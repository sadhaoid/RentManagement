package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

@SpringBootApplication
public class RentManagement {

    void main() {
        System.out.println("Starting AiXiaoyuServerApplication...");
		SpringApplication.run(RentManagement.class);
	}

}
