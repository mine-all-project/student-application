package org.example.application.common.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * springCache配置类
 */
@Configuration
@CacheConfig
@EnableCaching
public class CacheAbleConfigure extends CachingConfigurerSupport {
    private final RedisConnectionFactory redisConnectionFactory;

    public CacheAbleConfigure(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Bean
    public CacheManager cacheManager() {
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair
                .fromSerializer(fastJsonRedisSerializer))
                //不序列化null字段
                .disableCachingNullValues()
                //缓存时间 10分钟
                .entryTtl(Duration.ofMinutes(10));
        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(configuration).build();
    }

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer)).entryTtl(Duration.ofMinutes(5));
        return configuration;
    }
}
