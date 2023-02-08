package com.sunlife.vn.config;

import com.sunlife.vn.properties.ApplicationAwsProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

public class ApplicationAwsPropertiesBaseConfig {
    
    @Bean
    @ConfigurationProperties(prefix = "application.aws")
    public ApplicationAwsProperties applicationAwsProperties() {
        return new ApplicationAwsProperties();
    }
}
