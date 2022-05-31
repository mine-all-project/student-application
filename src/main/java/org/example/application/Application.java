package org.example.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@EnableJpaAuditing
@SpringBootApplication
@EnableScheduling
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws ClassNotFoundException {
        SpringApplication.run(Application.class, args);
        logger.info(">>>>>>>>服务启动成功 [jpa] [jwt] >>>>>>>>>");
    }
    //1bbd886460827015e5d605ed44252251

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
