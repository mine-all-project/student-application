package org.example.application.common;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

/**
 * 请求入参解密
 */
//@Component
//@ControllerAdvice
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
        return inputMessage;
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
