package org.example.fangwuzulin.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Value;

public class RandomCodeUtils {
    @Value("${crabapples.salt}")
    private String salt;

    public String md5Hash(String str) {
        return new Md5Hash(str, salt).toString();
    }
}
