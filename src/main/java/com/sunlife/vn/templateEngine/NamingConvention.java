package com.sunlife.vn.templateEngine;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunlife.vn.ultil.AppConstant;
import com.sunlife.vn.ultil.DateTimeUtil;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface NamingConvention {
    String getValue();

    default NamingConvention replaceUserData(String userData) throws Exception {
        String result = this.getValue();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(userData);
        Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");
        Matcher matcher = pattern.matcher(this.getValue());

        while (matcher.find()) {
            String key = matcher.group(1);
            String[] nameInfos = key.split(":");
            String nameValue = node.get(nameInfos[0]).asText();

            if (nameInfos.length > 1) {
                /*format data by pattern*/
                nameValue = NamingConvention.formatByPattern(nameValue, nameInfos[1]);
            }
            result = result.replace("${" + key + "}", nameValue);
        }

        String finalResult = result;
        return () -> {
            return finalResult;
        };
    }

    default NamingConvention replaceUserDataEmptyIfNull(String userData) throws Exception {
        String result = this.getValue();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(userData);
        Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");
        Matcher matcher = pattern.matcher(this.getValue());

        while (matcher.find()) {
            String key = matcher.group(1);
            String[] nameInfos = key.split(":");
            String nameValue = "";
            nameValue = node.at("/" + nameInfos[0]).asText();
            if (nameInfos.length > 1) {
                /*format data by pattern*/
                nameValue = NamingConvention.formatByPattern(nameValue, nameInfos[1]);
            }
            result = result.replace("${" + key + "}", nameValue);
        }

        String finalResult = result;
        return () -> {
            return finalResult;
        };
    }

    default NamingConvention replaceUserDataArray(String userData) throws Exception {
        String result = this.getValue();
        List<String> listResult = new ArrayList<String>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(userData);
        Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");
        Matcher matcher = pattern.matcher(this.getValue());

        while (matcher.find()) {
            String key = matcher.group(1);
            String[] nameInfos = key.split(":");
            String nameValue = "";
//            nameValue = node.at("/" + nameInfos[0]).asText();

            JsonNode replaceValue = node.at("/" + nameInfos[0]);
            if (replaceValue.isArray()) {
                for (JsonNode jsonNode : replaceValue) {
                    nameValue = jsonNode.asText();
                    if (nameInfos.length > 1) {
                        nameValue = NamingConvention.formatByPattern(nameValue, nameInfos[1]);
                    }
                    listResult.add(result.replace("${" + key + "}", nameValue));
                }
            } else {
                nameValue = node.at("/" + nameInfos[0]).asText();
                if (nameInfos.length > 1) {
                    /*format data by pattern*/
                    nameValue = NamingConvention.formatByPattern(nameValue, nameInfos[1]);
                }
                listResult.add(result.replace("${" + key + "}", nameValue));
            }
        }

        if (listResult.isEmpty()) {
            listResult.add(result);
        }
        String finalResult = mapper.writeValueAsString(listResult);
        return () -> {
            return finalResult;
        };
    }

    default NamingConvention replaceSystemData() throws Exception {
        String result = this.getValue();
        Pattern pattern = Pattern.compile("\\%(.*?)\\%");
        Matcher matcher = pattern.matcher(this.getValue());
        while (matcher.find()) {
            String key = matcher.group(1);
            String[] nameInfos = key.split(":");
            String nameValue = NamingConvention.getSystemData(nameInfos[0]);

            if (nameInfos.length > 1) {
                nameValue = NamingConvention.formatByPattern(nameValue, nameInfos[1]);
            }

            result = result.replace("%" + key + "%", nameValue);
        }
        String finalResult = result;
        return () -> {
            return finalResult;
        };
    }

    static String getSystemData(String key) {
        switch (key) {
            case "date": {
                LocalDateTime dateTime = LocalDateTime.now();
                return DateTimeUtil.convertToStringByFormat(dateTime, AppConstant.DATETIME_YYYYMMDD_HHMMSS);
            }
            case "uuid": {
                return UUID.randomUUID().toString().replace("-", "");
            }
            default: {
                return "";
            }
        }
    }

    static String formatByPattern(String data, String format) throws Exception {
        try {
            LocalDateTime dateTime = DateTimeUtil.convertStringToLocalDateTime(data, AppConstant.DATETIME_YYYYMMDD_HHMMSS);
            switch (format) {
                case "yyyyMMdd": {
                    return DateTimeUtil.convertToStringByFormat(dateTime, AppConstant.DATE_YYYYMMDD);
                }
                case "ddmmyyyy": {
                    return DateTimeUtil.convertToStringByFormat(dateTime, AppConstant.DATE_DDMMYYYY);
                }
                case "hhmmss": {
                    return DateTimeUtil.convertToStringByFormat(dateTime, AppConstant.TIME_HHMMSS);
                }
                case "yyyy_MM_dd": {
                    return DateTimeUtil.convertToStringByFormat(dateTime, AppConstant.DATE_YYYY_MM_DD);
                }
                case "yyyy_MM_dd_HH_mm_ss": {
                    return DateTimeUtil.convertToStringByFormat(dateTime, AppConstant.DATETIME_YYYY_MM_DD_HH_MM_SS);
                }
                case "date": {
                    return DateTimeUtil.convertToStringByFormat(dateTime, AppConstant.DATETIME_YYYYMMDD_HHMMSS);
                }
            }
            return "";
        } catch (Exception ex) {
            throw new Exception("Format: " + format + " is not compatible with input data!");
        }
    }

    static String formatByPattern(String data, String format, String localFormat) throws Exception {
        try {
            LocalDateTime dateTime = DateTimeUtil.convertStringToLocalDateTime(data, localFormat);
            switch (format) {
                case "yyyyMMdd": {
                    return DateTimeUtil.convertToStringByFormat(dateTime, AppConstant.DATE_YYYYMMDD);
                }
                case "ddmmyyyy": {
                    return DateTimeUtil.convertToStringByFormat(dateTime, AppConstant.DATE_DDMMYYYY);
                }
                case "hhmmss": {
                    return DateTimeUtil.convertToStringByFormat(dateTime, AppConstant.TIME_HHMMSS);
                }
                case "date": {
                    return DateTimeUtil.convertToStringByFormat(dateTime, AppConstant.DATETIME_YYYYMMDD_HHMMSS);
                }
            }
            return "";
        } catch (Exception ex) {
            throw new Exception("Format: " + format + " is not compatible with input data!");
        }
    }
}
