package cn.crabapples.tuole;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentApplication {
    private static final Logger logger = LoggerFactory.getLogger(StudentApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
        logger.info("项目启动完成-->>");
    }
}
