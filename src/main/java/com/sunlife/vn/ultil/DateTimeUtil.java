package com.sunlife.vn.ultil;

import com.sunlife.vn.constant.Constant;
import org.apache.commons.lang3.StringUtils;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
    public static <T> String convertToStringByFormat(T date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        if (date instanceof LocalDate) {
            return formatter.format((LocalDate) date);
        } else if (date instanceof LocalDateTime) {
            return formatter.format((LocalDateTime) date);
        }
        return "";
    }

    public static LocalDateTime convertStringToLocalDateTime(String input, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(input, formatter);
    }

    public static String longToDateString(Long longDate) {
        Date date = new Date(longDate);
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        return formattedDate;
    }

    public static Date StringToDate(String dob) throws ParseException {
        // Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        // Parsing the given String to Date object
        Date date = formatter.parse(dob);
        return date;
    }

    public static Date StringToDateTime(String dob) throws ParseException {
        // Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        // Parsing the given String to Date object
        Date date = formatter.parse(dob);
        return date;
    }

    public static String timeStampToString(long time) throws ParseException {
        // Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        // Parsing the given String to Date object
        String timeString = formatter.format(time);
        return timeString;
    }

    public static String timeStampToStringWithTimeZone(long time) throws ParseException {
        // Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        // Parsing the given String to Date object
        String timeString = formatter.format(time);
        return timeString;
    }

    public static Timestamp StringToTimeStamp(String time) throws ParseException {
        // Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        // Parsing the given String to Date object
        Date date = formatter.parse(time);
        return new Timestamp(date.getTime());
    }

    public static String longToDateString(Long longDate, String format) {
        Date date = new Date(longDate);
        String formattedDate = new SimpleDateFormat(format).format(date);
        return formattedDate;
    }


    public static String[] getPeriod(Long longDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(longDate);
        String[] dateArr = new String[2];
        String from = cal.get(Calendar.YEAR) + "-" + String.format("%02d", (cal.get(Calendar.MONTH))) + "-25";
        dateArr[0] = from;
        String to = cal.get(Calendar.YEAR) + "-" + String.format("%02d", (cal.get(Calendar.MONTH) + 1)) + "-24";
        dateArr[1] = to;
        return dateArr;
    }

    public static String formatDateToString(Date input, String format) {
        String formattedDate = new SimpleDateFormat(format).format(input);
        return formattedDate;
    }

    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    public static String plusYearFromCurrent(int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        calendar.add(Calendar.YEAR, date);
        String newDate = sdf.format(calendar.getTime());
        return newDate;
    }

    public static String formatDate(Date date, String stringFormatter) {
        if (StringUtils.isBlank(stringFormatter)) stringFormatter = Constant.DATE_FORMAT;
        if (date == null) return null;
        SimpleDateFormat formatter = new SimpleDateFormat(stringFormatter);
        return formatter.format(date);
    }

    public static Date parseDate(String stringDate, String stringFormatter) throws ParseException {
        try {
            if (StringUtils.isBlank(stringFormatter)) stringFormatter = Constant.DATE_FORMAT;
            SimpleDateFormat formatter = new SimpleDateFormat(stringFormatter);
            return formatter.parse(stringDate);
        } catch (ParseException e) {
            throw e;
        }
    }

    public static Date isValidDate(String date, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        formatter.setLenient(false);
        try {
            Date dateParse = formatter.parse(date);
            return dateParse;
        } catch (ParseException e) {
            return null;
        }
    }
}
