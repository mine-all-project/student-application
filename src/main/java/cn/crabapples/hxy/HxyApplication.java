package cn.crabapples.hxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HxyApplication {
    public static void main(String[] args) {
        SpringApplication.run(HxyApplication.class, args);
        System.err.println("启动成功");
    }
}
