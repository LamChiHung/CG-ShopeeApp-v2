package com.cgshopeeappv2.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary("cloudinary://914249571952293:_ikUsLCNO2xbGueXSkFu1rlluV8@dk32hbwiu");
    }
}
