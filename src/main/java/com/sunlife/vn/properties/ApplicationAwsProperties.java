package com.sunlife.vn.properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationAwsProperties {

    private String region;
    private S3Properties s3;
    private SfnProperties sfn;

    @Getter
    @Setter
    public static class S3Properties {
        private String bucket;
        private String key;
        private String uploadFileKey;
        private Boolean enabled;
    }
    
    @Getter
    @Setter
    public static class SfnProperties {
        private String arn;
        private Boolean enabled;
    }
}
