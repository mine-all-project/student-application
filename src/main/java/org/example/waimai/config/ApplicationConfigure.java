package org.example.waimai.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix= "crabapples")


public class ApplicationConfigure {
    /**
     * aesKey种子
     */
    public String AES_KEY;
    /**
     * redis缓存前缀
     */
    public String REDIS_PREFIX;
    /**
     * token缓存时间
     */
    public Long TOKEN_CACHE_TIME;
    /**
     * MD5加密盐值
     */
    public String SALT;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
