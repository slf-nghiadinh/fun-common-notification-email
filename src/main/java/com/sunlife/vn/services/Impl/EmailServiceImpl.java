package com.sunlife.vn.services.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.sunlife.vn.Engine.AmazonS3Engine;
import com.sunlife.vn.constant.Constant;
import com.sunlife.vn.models.TaskRequest;
import com.sunlife.vn.services.EmailService;
import com.sunlife.vn.templateEngine.NamingConvention;
import com.sunlife.vn.ultil.JacksonUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.sunlife.vn.ultil.JsonUtil;
import com.sunlife.vn.ultil.MailUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import java.io.StringReader;
import java.util.Map;
@Service
public class EmailServiceImpl implements EmailService {
    private AmazonS3Engine amazonS3Engine;



    private  Map<String,String> config;


    private String tempFileName;

    private String tempImg;


    @Autowired
    private ObjectMapper mapper;


    @Value("${connection.mail.black-list}")
    private String blackListConf;
    @Value("${connection.mail.white-list}")
    private String whiteListConf;
    @Override
    public String sendEmail(TaskRequest request) throws Exception {
        amazonS3Engine = new AmazonS3Engine();
        config = new HashMap<>();
        ObjectNode data = (ObjectNode) JacksonUtil.objectToJsonNode(request.getMailSend());
        ObjectNode EmailData = (ObjectNode)JacksonUtil.objectToJsonNode(request.getUserdata());
        String S3bucket = data.get("S3bucKet").asText();
        String S3type = data.get("S3UrlType").asText();
        String MailType = data.get("mailType").asText();
        tempFileName = "temp" + request.getEventId();
        tempImg = "tempImg" + request.getEventId();

        config.put("host","slf-antispam.glb.ca.sunlife");
        config.put("port","25");
        config.put("protocol","smtp");
        config.put("auth","false");
        config.put("starttls","false");
        config.put("debug","true");

        sendMessageUsingFreemarkerTemplate(EmailData,request,MailType,S3bucket,S3type);
        return null;
    }

    public void sendMessageUsingFreemarkerTemplate(JsonNode EmailData,TaskRequest taskRequest,String MailType,String S3Bucket, String S3Url) throws Exception {

        //String S3bucket = emailData.get("s3bucket").asText();

       // String S3Url = emailData.get("s3template").asText();



        //InputStream templateIS = amazonS3Engine.getObjectAsStream(S3Bucket,S3Url);
        InputStream templateIS = ClassLoader.getSystemResourceAsStream("Template_Email/Pos_Email.html");

       // assert templateIS != null;
        String strReader = new BufferedReader(new InputStreamReader(templateIS))
                .lines().collect(Collectors.joining("\n"));
        // get mail template
        Template freemarkerTemplate = new Template(MailType, new StringReader(strReader), new Configuration());
        // fill data to body
        Map<String, Object> emailDataMap = JacksonUtil.jsonNodeToMap(EmailData);
        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, emailDataMap);
        sendHtmlMessage(EmailData, htmlBody,taskRequest);
    }

    private void sendHtmlMessage(JsonNode emailData, String htmlBody , TaskRequest taskRequest) throws Exception {
        ObjectNode jsonConfig =  (ObjectNode)JacksonUtil.objectToJsonNode( taskRequest.getMailConfig());

        String isPassword = taskRequest.getSendPassword();

        String mailTemplate = taskRequest.getMailTemplate();

        // get data config
        JsonNode emailConfig = JacksonUtil.objectToJsonNode(jsonConfig);
        JsonNode mailSend = JacksonUtil.objectToJsonNode(taskRequest.getMailSend());
        ObjectNode checkPassword =  (ObjectNode) JacksonUtil.objectToJsonNode(isPassword);
        String sendPassword = mailSend.get("isSendPassword").asText();
        JsonNode imageConfig = JacksonUtil.objectToJsonNode(mailTemplate);
        List<String> blackList = Arrays.asList(blackListConf.split(","));
        List<String> whiteList = Arrays.asList(whiteListConf.split(","));

        // build message
        JavaMailSender emailSender = MailUtil.getMailSender(config);
        MimeMessage message = this.buildMimeMessage(emailConfig, emailData, blackList, whiteList);

        // build content
        MimeMultipart multipart = new MimeMultipart("related");
        // body
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(htmlBody, "text/html; charset=UTF-8");
        multipart.addBodyPart(messageBodyPart);
        // image inline
        if (!imageConfig.isEmpty()) {
            Map<String, Object> imageMap = JacksonUtil.objectToMap(imageConfig);
            for (Map.Entry<String, Object> entry : imageMap.entrySet()) {
                messageBodyPart = new MimeBodyPart();
                byte[] imgBytes = Base64.getDecoder().decode(entry.getValue().toString().split(",")[1]);
                DataSource fds = new ByteArrayDataSource(imgBytes, "image/png");
                messageBodyPart.setDataHandler(new DataHandler(fds));
                messageBodyPart.setHeader("Content-ID", "<" + entry.getKey() + ">");
                messageBodyPart.setDisposition(MimeBodyPart.INLINE);
                multipart.addBodyPart(messageBodyPart);
            }
        }
        // attachments
        if ((!emailConfig.has(Constant.EMAILCONFIG.hasAttachments.name()))
                ||
                (emailConfig.has(Constant.EMAILCONFIG.hasAttachments.name()) && JsonUtil.extractJsonBoolean(emailConfig.get(Constant.EMAILCONFIG.hasAttachments.name())))) {

            if (emailData.has(Constant.EMAILCONFIG.attachments.name())) {
                String attachment = emailData.get(Constant.EMAILCONFIG.attachments.name()).asText();
                if (attachment.isEmpty()) {
                    attachment = emailData.get(Constant.EMAILCONFIG.attachments.name()).toString();
                }
                List<String> filePaths = mapper.readValue(attachment,
                        TypeFactory.defaultInstance().constructCollectionType(List.class, String.class));
                String attachmentName = FilenameUtils.getBaseName(filePaths.get(0));
                if (emailConfig.has(Constant.EMAILCONFIG.password.name())) {
                    String password = "";
                    if (emailData.has(Constant.EMAILCONFIG.password.name())) {
                        password = emailData.get(Constant.EMAILCONFIG.password.name()).asText();
                    } else {
                        password = MailUtil.generateAttachmentPassword(10);
                    }
                    ZipFile zipFile = compressFiles(filePaths, password);
                    messageBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(zipFile.getFile());
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(FilenameUtils.getName(attachmentName + ".zip"));
                    multipart.addBodyPart(messageBodyPart);
                    ((ObjectNode) emailData).put(Constant.EMAILCONFIG.password.name(), password);

                } else {
                    for (String path : filePaths) {
                        messageBodyPart = new MimeBodyPart();
                        DataSource source = new FileDataSource(path);
                        messageBodyPart.setDataHandler(new DataHandler(source));
                        messageBodyPart.setFileName(FilenameUtils.getName(path));
                        multipart.addBodyPart(messageBodyPart);
                    }
                }
            }
        }
        // set content
        message.setContent(multipart, "UTF-8");
        // send mail
        emailSender.send(message);

        // delete temp
        Files.deleteIfExists(Paths.get(tempImg));
        Files.deleteIfExists(Paths.get(tempFileName));

        if (sendPassword.equals("T")) {
            String typePassword = checkPassword.get("isPasswordType").asText();

            String S3BucKet = checkPassword.get("S3BucketPassword").asText();
            String S3Url = checkPassword.get("S3UrlPassword").asText();
            checkPassword.put("isSendPassword","F");
            ObjectMapper passwordMapper = new ObjectMapper();
            taskRequest.setUserdata(passwordMapper.writeValueAsString(checkPassword));
            // String emailType = emailConfig.get(Constant.EMAILCONFIG.password.name()).asText();
            if (StringUtils.isNotBlank(typePassword)) {
                Thread.sleep(3000);
                sendPasswordEmail(emailData, typePassword,taskRequest,S3BucKet,S3Url);
            }
        }
    }


    private ZipFile compressFiles(List<String> filePaths, String password) throws ZipException {
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setEncryptFiles(true);
        zipParameters.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD);
        zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);

        List<File> filesToAdd = filePaths.stream().map(path -> new File(path)).collect(Collectors.toCollection(ArrayList::new));

        ZipFile zipFile = new ZipFile(tempFileName, password.toCharArray());
        zipFile.addFiles(filesToAdd, zipParameters);

        return zipFile;
    }

    private MimeMessage buildMimeMessage(JsonNode emailConfig, JsonNode emailData, List<String> blackList, List<String> whiteList) throws Exception {
        // init mail message
        JavaMailSender emailSender = MailUtil.getMailSender(config);
        MimeMessage message = emailSender.createMimeMessage();
        // FROM
        message.setFrom(new InternetAddress(emailConfig.get(Constant.EMAILCONFIG.fromAddress.name()).asText(), emailConfig.get(Constant.EMAILCONFIG.fromName.name()).asText(), "utf-8"));
        // SUBJECT
        if (emailData.has(Constant.EMAILCONFIG.subject.name())) {
            message.setSubject(emailData.get(Constant.EMAILCONFIG.subject.name()).asText(), "UTF-8");
        } else {
            String subjectTemplate = emailConfig.get(Constant.EMAILCONFIG.subject.name()).asText();
            NamingConvention namingConvention = () -> {
                return subjectTemplate;
            };
            message.setSubject(namingConvention.replaceSystemData().replaceUserDataEmptyIfNull(emailData.toString()).getValue(), "UTF-8");
        }
        // TO
        String to = emailData.get(Constant.EMAILCONFIG.to.name()).asText();
        message.setRecipients(Message.RecipientType.TO, parseAndCheckEmail(to, blackList, whiteList));
        // CC
        if (emailData.has(Constant.EMAILCONFIG.cc.name())) {
            String cc = emailData.get(Constant.EMAILCONFIG.cc.name()).asText();
            if (emailConfig.has(Constant.EMAILCONFIG.cc.name())) {
                cc += ", " + emailConfig.get(Constant.EMAILCONFIG.cc.name()).asText();
            }
            message.setRecipients(Message.RecipientType.CC, parseAndCheckEmail(cc, blackList, whiteList));
        } else if (emailConfig.has(Constant.EMAILCONFIG.cc.name())) {
            message.setRecipients(Message.RecipientType.CC, parseAndCheckEmail(emailConfig.get(Constant.EMAILCONFIG.cc.name()).asText(), blackList, whiteList));
        }
        // BCC
        if (emailData.has(Constant.EMAILCONFIG.bcc.name())) {
            String bcc = emailData.get(Constant.EMAILCONFIG.bcc.name()).asText();
            if (emailConfig.has(Constant.EMAILCONFIG.bcc.name())) {
                bcc += ", " + emailConfig.get(Constant.EMAILCONFIG.bcc.name()).asText();
            }
            message.setRecipients(Message.RecipientType.BCC, parseAndCheckEmail(bcc, blackList, whiteList));
        } else if (emailConfig.has(Constant.EMAILCONFIG.bcc.name())) {
            message.setRecipients(Message.RecipientType.BCC, parseAndCheckEmail(emailConfig.get(Constant.EMAILCONFIG.bcc.name()).asText(), blackList, whiteList));
        }

        return message;
    }


    private InternetAddress[] parseAndCheckEmail(String emails, List<String> blackList, List<String> whiteList) throws AddressException {
        Set<String> emailSet = new HashSet<String>(Arrays.asList(emails.replace(";", ",").toLowerCase().split("\\s*,\\s*")));
        Set<String> notBlackSet = new HashSet<String>(emailSet);
        // String regex = "^(.+)@sunlife.com";

        // black list
        for (String regex : blackList) {
            Pattern pattern = Pattern.compile(regex);
            for (Iterator<String> iterator = notBlackSet.iterator(); iterator.hasNext(); ) {
                String email = iterator.next();
                Matcher matcher = pattern.matcher(email.trim());
                if (matcher.matches()) {
                    iterator.remove();
                }
            }
        }
        // white list
        Set<String> whiteSet = new HashSet<String>();
        for (String regex : whiteList) {
            Pattern pattern = Pattern.compile(regex);
            for (Iterator<String> iterator = emailSet.iterator(); iterator.hasNext(); ) {
                String email = iterator.next();
                Matcher matcher = pattern.matcher(email.trim());
                if (matcher.matches()) {
                    whiteSet.add(email.trim());
                }
            }
        }

        emailSet.clear();
        emailSet.addAll(notBlackSet);
        emailSet.addAll(whiteSet);

        return InternetAddress.parse(emailSet.toString().substring(1, emailSet.toString().length() - 1));
    }
    private void sendPasswordEmail(JsonNode emailData, String mailType,TaskRequest taskRequest,String S3Bucket, String S3Url) throws Exception {
        try {
            // get mail config
            // send mail
            sendMessageUsingFreemarkerTemplate(emailData,taskRequest,mailType,S3Bucket,S3Url);

        } catch (Exception e) {

            e.printStackTrace();
            throw e;
        } finally {

        }
    }

}
