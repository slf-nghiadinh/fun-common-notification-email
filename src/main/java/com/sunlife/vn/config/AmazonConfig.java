package com.sunlife.vn.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sfn.SfnClient;

public class AmazonConfig {
   private String region_name="ap-southeast-1";

    @Bean
    @ConditionalOnProperty(
            value = "application.aws.s3.enabled",
            havingValue = "true"
    )
    public S3Client s3Client() {
        return S3Client
                .builder()
                .region(Region.of(region_name))
                .build();
    }

    public SfnClient sfnClient() {
        return SfnClient
                .builder()
                .region(Region.of(region_name))
                .build();
    }

    @Bean
    @ConditionalOnProperty(
            value = "application.aws.dynamodb.enabled",
            havingValue = "true"
    )
    public DynamoDbClient dynamoDbClient() {
        return DynamoDbClient
                .builder()
                .region(Region.of(region_name))
                .build();
    }

    @Bean
    @ConditionalOnProperty(
            value = "application.aws.dynamodb.enabled",
            havingValue = "true"
    )
    public DynamoDbEnhancedClient dynamoDbEnhancedClient() {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient())
                .build();
    }



}
