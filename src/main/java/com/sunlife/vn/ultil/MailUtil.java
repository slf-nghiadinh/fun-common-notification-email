package com.sunlife.vn.ultil;

import com.sunlife.vn.constant.Constant;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Map;
import java.util.Properties;

public class MailUtil {
    private static ThreadLocal<JavaMailSender> local = new ThreadLocal<JavaMailSender>();

    public static JavaMailSender getMailSender(Map<String, String> config) {
        if (local.get() == null) {
            initMailSender(config);
        }

        return local.get();
    }

    public static String generateAttachmentPassword(int len) {
        return RandomStringUtils.randomAlphanumeric(len);
    }

    private static void initMailSender(Map<String, String> config) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(config.get(Constant.MAILSENDERCONFIG.host.name()));
        mailSender.setPort(Integer.parseInt(config.get(Constant.MAILSENDERCONFIG.port.name())));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", config.get(Constant.MAILSENDERCONFIG.protocol.name()));
        props.put("mail.smtp.auth", config.get(Constant.MAILSENDERCONFIG.auth.name()));
        props.put("mail.smtp.starttls.enable", config.get(Constant.MAILSENDERCONFIG.starttls.name()));
        props.put("mail.debug", config.get(Constant.MAILSENDERCONFIG.debug.name()));
        props.put("mail.mime.allowutf8", "true");
//        props.put("mail.smtp.ssl.enable", "true");

        local.set(mailSender);
    }
}
