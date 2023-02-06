package com.sunlife.vn.Engine;

import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class AmazonS3Engine {
    @Autowired(required = false)
    private S3Client s3Client;

    public InputStream getObjectAsStream(String bucket, String key) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();

        return s3Client.getObject(getObjectRequest);
    }


    public String putObject(String bucket, String key, OutputStream outputStream) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();

        byte[] dataAsByte = ((ByteArrayOutputStream) outputStream).toByteArray();
        RequestBody requestBody = RequestBody.fromBytes(dataAsByte);

        PutObjectResponse putObjectResponse = s3Client.putObject(putObjectRequest, requestBody);
        return putObjectResponse.eTag();
    }


}
