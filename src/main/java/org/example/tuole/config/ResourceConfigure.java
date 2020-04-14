package org.example.tuole.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * TODO
 *
 *
 * 2020/4/3 1:12
 *
 *
 * pc-name 29404
 */
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
