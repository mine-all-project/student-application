package org.example.yangjichang.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfigure implements WebMvcConfigurer {
    @Value("${filePath}")
    private String filePath;

    /**
     * 虚拟路径配置
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations("file:" + filePath + "/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
