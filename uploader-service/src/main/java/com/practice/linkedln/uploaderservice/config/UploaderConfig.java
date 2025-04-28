package com.practice.linkedln.uploaderservice.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class UploaderConfig {

    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.api-key}")
    private String apikey;

    @Value("${cloudinary.api-secret}")
    private String apisecret;



    @Bean
    public Cloudinary cloudinary(){
        Map<String,String>  config = Map.of(
            "cloud_name",cloudName,
            "api_key",apikey,
            "api_secret",apisecret
        );
        return new Cloudinary(config);
    }

}
