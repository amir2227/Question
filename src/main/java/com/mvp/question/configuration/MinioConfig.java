package com.mvp.question.configuration;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {
    @Value("${minio.access-key}")
    private String accessKey;
    @Value("${minio.secret-key}")
    private String secretKet;
    @Value("${minio.url}")
    private String url;
    @Value("${minio.bucket}")
    private String bucket;

    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient = MinioClient.builder().endpoint(url)
                .credentials(accessKey, secretKet).build();
        try {
            System.out.println(url);
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder()
                    .bucket(bucket).build());
            if (!isExist) {
                minioClient.makeBucket(MakeBucketArgs.builder()
                        .bucket(bucket)
                        .build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return minioClient;
    }

}
