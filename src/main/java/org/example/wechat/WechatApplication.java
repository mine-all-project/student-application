package org.example.wechat;

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
//@NacosPropertySource(dataId = "wechat-dev.properties", autoRefreshed = true)
@NacosPropertySource(dataId = "gongjiao-dev.properties", autoRefreshed = true)
public class WechatApplication {
    private static final Logger logger = LoggerFactory.getLogger(WechatApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(WechatApplication.class, args);
        logger.info("项目启动完成-->>");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
