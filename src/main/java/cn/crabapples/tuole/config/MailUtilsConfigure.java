package cn.crabapples.tuole.config;


import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO 邮件配置类
 *
 * @author Mr.He
 * 2020/3/20 21:15
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Getter
@Setter
public class MailUtilsConfigure {
    private String host = "smtp.qq.com";
    private String source = "294046317@qq.com";
    private String port = "465";
    private String username = "294046317@qq.com";
    private String password = "LILI5201";;
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
