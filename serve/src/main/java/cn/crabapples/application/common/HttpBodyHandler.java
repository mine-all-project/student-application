package cn.crabapples.application.common;

import cn.crabapples.application.common.utils.security.RsaUtils;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * TODO 请求入参解密
 *
 * @author Mr.He
 * 2021/3/25 2:29
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
@Component
@ControllerAdvice
public class HttpBodyHandler implements RequestBodyAdvice {
    //    @Value("${privateKey}")
    private final String privateKey;
    @Value("${crypt:false}")
    private boolean crypt;

    public HttpBodyHandler() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("private.key");
        InputStream inputStream = classPathResource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder privateKey = new StringBuilder();
        for (String line = ""; line != null; line = reader.readLine()) {
            System.err.println(line);
            privateKey.append(line);
        }
        this.privateKey = privateKey.toString();
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        InputStream inputStream = inputMessage.getBody();
        StringBuilder body = new StringBuilder();
        byte[] data = new byte[1024];
        for (int index = 0; index != -1; index = inputStream.read(data)) {
            body.append(new String(data, StandardCharsets.UTF_8));
        }
        inputStream.close();
        String encBodyStr = body.toString().replace("%2F", "/").replace("%3D", "=");
        byte[] encBodyByte = Base64.decodeBase64(encBodyStr.getBytes(StandardCharsets.UTF_8));
        String bodyString = crypt ? RsaUtils.decryptByteArray(encBodyByte, privateKey) : body.toString();
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(bodyString.getBytes(StandardCharsets.UTF_8));
        System.err.println(bodyString);
        return new HttpInputMessage() {
            @Override
            public HttpHeaders getHeaders() {
                return inputMessage.getHeaders();
            }

            @Override
            public InputStream getBody() {
                return arrayInputStream;
            }
        };
    }


    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return null;
    }
}
