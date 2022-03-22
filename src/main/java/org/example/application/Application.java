package org.example.application;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@EnableJpaAuditing
@SpringBootApplication
@MapperScan({"org.example.application.custom.dao.mybatis"})
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws ClassNotFoundException {
        SpringApplication.run(Application.class, args);
        logger.info(">>>>>>>>服务启动成功 [jpa] [jwt] >>>>>>>>>");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
