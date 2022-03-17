package org.example.application.common.utils;

import com.google.code.kaptcha.Producer;

import java.awt.image.BufferedImage;

public class RandomCodeUtils {
    public RandomCode createRandomCode(Producer producer) {
        String code = producer.createText();
        BufferedImage image = producer.createImage(code);
        return new RandomCode(code, image);
    }
}
