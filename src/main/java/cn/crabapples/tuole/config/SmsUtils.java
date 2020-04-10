package cn.crabapples.tuole.config;

import com.alibaba.fastjson.JSONObject;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * TODO
 *
 * @author Mr.He
 * 2020/4/9 21:18
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Component
public class SmsUtils {
    @Value("${sms.secretId}")
    private String secretId;
    @Value("${sms.secretKey}")
    private String secretKey;
    private static final String SIGN = "枕竹知雨韵";
    private static final String APP_ID = "1400344819";

    public String sendNoticeMessage(String phone, String username, String goodsName) throws TencentCloudSDKException {
        Credential cred = new Credential(secretId, secretKey);
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("sms.tencentcloudapi.com");
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        SmsClient client = new SmsClient(cred, "", clientProfile);
        SendSmsRequest req = SendSmsRequest.fromJsonString(createNoticeSms(phone, username, goodsName), SendSmsRequest.class);
        SendSmsResponse resp = client.SendSms(req);
        String result = SendSmsRequest.toJsonString(resp);
        System.out.println(result);
        return result;
    }

    public String sendCodeMessage(String phone, String code) throws TencentCloudSDKException {
        Credential cred = new Credential(secretId, secretKey);
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("sms.tencentcloudapi.com");
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        SmsClient client = new SmsClient(cred, "", clientProfile);
        SendSmsRequest req = SendSmsRequest.fromJsonString(createCodeSms(phone, code), SendSmsRequest.class);
        SendSmsResponse resp = client.SendSms(req);
        String result = SendSmsRequest.toJsonString(resp);
        System.out.println(result);
        return result;
    }

    private String createNoticeSms(String phone, String username, String goodsName) {
        String templateId = "568576";
        Set<String> phones = new HashSet<>();
        phones.add("+86" + phone);
        List<String> templateParams = new LinkedList<>();
        templateParams.add(username);
        templateParams.add(goodsName);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("PhoneNumberSet", phones);
        jsonObject.put("TemplateID", templateId);
        jsonObject.put("Sign", SIGN);
        jsonObject.put("TemplateParamSet", templateParams);
        jsonObject.put("SmsSdkAppid", APP_ID);
        System.err.println("短信内容 ：" + jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }

    private String createCodeSms(String phone, String code) {
        String templateId = "568575";
        Set<String> phones = new HashSet<>();
        phones.add("+86" + phone);
        Set<String> templateParams = new HashSet<>();
        templateParams.add(code);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("PhoneNumberSet", phones);
        jsonObject.put("TemplateID", templateId);
        jsonObject.put("Sign", SIGN);
        jsonObject.put("TemplateParamSet", templateParams);
        jsonObject.put("SmsSdkAppid", APP_ID);
        System.err.println("短信内容 ：" + jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }
}
