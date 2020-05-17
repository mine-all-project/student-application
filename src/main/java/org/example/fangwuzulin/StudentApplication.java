package org.example.fangwuzulin;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan("org.example.fangwuzulin.mapping")
public class StudentApplication {
    private static final Logger logger = LoggerFactory.getLogger(StudentApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
        logger.info("项目启动完成-->>");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
