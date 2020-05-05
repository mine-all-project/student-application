package org.example.fangwuzulin;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@EnableJpaAuditing
@SpringBootApplication
@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "192.168.3.20:8848"))
@NacosPropertySource(dataId = "yangjichang-dev.properties", autoRefreshed = true)
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
