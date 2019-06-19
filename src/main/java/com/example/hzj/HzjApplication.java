package com.example.hzj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HzjApplication {
    public static void main(String[] args) {
        SpringApplication.run(HzjApplication.class, args);
        System.err.println("欢迎使用遵义医科大学双选会系统");
    }
}
