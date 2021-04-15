package cn.crabapples.application.common.utils.jwt;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * jwt配置类
 */
@Getter
@Setter
@Component
@PropertySource(value = {"classpath:application-custom.properties"})
@ConfigurationProperties(prefix = "crabapples.jwt")
public class JwtConfigure {
    private String authKey;
    private String clientId;
    private String base64Secret;
    private String name;
    private int expiresSecond;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
