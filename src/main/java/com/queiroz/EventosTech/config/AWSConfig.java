package com.queiroz.EventosTech.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Bean
    public AmazonS3 createS3Instance() {
        return AmazonS3ClientBuilder.standard()
                .withRegion(bucketName)
                .build();
    }
}
