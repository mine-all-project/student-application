package cn.crabapples.application.common.utils.mail;


import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 邮件配置类
 */
@Getter
@Setter
@Configuration
@PropertySource(value = {"classpath:application-custom.properties"})
@ConfigurationProperties(prefix = "crabapples.mail")
public class MailConfigure {
    private String host;
    private String source;
    private String port;
    private String username;
    private String password;
    private boolean isAuth;
    private boolean isDebug;
    private String protocol;
    private String socketFactory;
    private String[] targets = {};

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
