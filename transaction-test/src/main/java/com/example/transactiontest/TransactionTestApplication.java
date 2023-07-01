package com.example.transactiontest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TransactionTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionTestApplication.class, args);
    }

}
