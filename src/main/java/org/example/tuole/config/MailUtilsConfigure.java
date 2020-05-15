package org.example.tuole.config;


import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailUtilsConfigure {
    private String host = "smtp.qq.com";
    private String source = "294046317@qq.com";
    private String port = "465";
    private String username = "294046317@qq.com";
    private String password = "LILI5202";
    private boolean isAuth = true;
    private boolean isDebug = true;
    private String protocol = "smtp";
    private String socketFactory = "javax.net.ssl.SSLSocketFactory";
    private String[] targets = {"162165436@qq.com"};

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
