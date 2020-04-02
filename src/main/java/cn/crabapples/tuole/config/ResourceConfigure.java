package cn.crabapples.tuole.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * TODO
 *
 * @author Mr.He
 * 2020/4/2 21:54
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Configuration
public class ResourceConfigure implements WebMvcConfigurer {
    /**
     * 虚拟路径配置
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations("file:/D:/test/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}