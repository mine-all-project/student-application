package org.example.application.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfigure implements WebMvcConfigurer {
    @Value("${uploadPath}")
    private String uploadPath;
    @Value("${virtualPath}")
    private String virtualPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(virtualPath + "/**").addResourceLocations("file:" + uploadPath + "/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
