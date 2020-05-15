package org.example.waimai.dto;

import com.alibaba.fastjson.JSONObject;

import java.awt.image.BufferedImage;



public class RandomCode {
    public RandomCode(String code, BufferedImage image) {
        this.code = code;
        this.image = image;
    }

    private String code;
    private BufferedImage image;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
