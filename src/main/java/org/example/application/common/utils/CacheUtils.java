package org.example.application.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 缓存工具类
 */
@Component
@Slf4j
public class CacheUtils {
    private final RedisTemplate<String, Object> redisTemplate;

    public CacheUtils(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void set(String key, Object value, long time) {
        log.debug("需要缓存的数据为-> key:[{}],value:[{}],时间为:[{}]秒", key, value, time);
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        log.info("数据-> key:[{}],时间:[{}]秒,已加入缓存", key, value);
    }

    public Object get(String key) {
        log.debug("需要获取的key为:[{}],", key);
        Object value = redisTemplate.opsForValue().get(key);
        log.debug("数据获取成功: key:[{}],value:[{}]", key, value);
        return value;
    }

    public Boolean remove(String key) {
        log.debug("需要删除的key为:[{}],", key);
        Boolean status = redisTemplate.delete(key);
        log.debug("数据删除成功: key:[{}],value:[{}]", key, status);
        return status;
    }
}
