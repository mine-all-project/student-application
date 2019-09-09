package cn.crabapples.hxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HxyApplication {
    public static void main(String[] args) {
        SpringApplication.run(HxyApplication.class, args);
        System.err.println("欢迎使用遵义医科大学双选会系统");
    }
}
