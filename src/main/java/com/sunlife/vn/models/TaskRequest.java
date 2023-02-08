package com.sunlife.vn.models;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {
    private String userdata;

    private String EventId;

    private String  mailConfig;

    private String  sendPassword;

    private String  mailSend;

    private String mailTemplate;
    @Getter
    @Setter
    public static class MailConfig{
        private String To;

        private String From;


        private String Subject;

        private String FromName;

        private String CC;

        private String Bcc;



    }

    @Getter
    @Setter
    public static  class  isSendPassword{


        private  String S3BucketPassword;

        private String S3UrlPassword;

        private String isPasswordType;

        private String Password;
    }

    @Setter
    @Getter
    public static class MailSend{

        private String mailType;

        private MailConfig mailConfig;

        private isSendPassword sendPassword;


        private TemplateEmail templateEmail;

        private String S3bucKet ;

        private String S3UrlType;

        private String configMail ;




        private String isAttach;

        private String Attachment;

        private String isSendPassword;



    }

    @Getter
    @Setter
    public static class TemplateEmail{
        private String S3buCKet;

        private String S3urlType;


        private List<String> ListImage;


    }

}




