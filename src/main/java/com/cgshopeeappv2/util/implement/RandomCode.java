package com.cgshopeeappv2.util.implement;

import com.cgshopeeappv2.util.IRandomCode;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomCode implements IRandomCode {

    @Override
    public String randomCode() {
        Random random = new Random();
        int a = random.nextInt(10);
        int b = random.nextInt(10);
        int c = random.nextInt(10);
        int d = random.nextInt(10);
        int e = random.nextInt(10);
        return String.valueOf(a) + String.valueOf(b) + String.valueOf(c) + String.valueOf(d) + String.valueOf(e);
    }
}
