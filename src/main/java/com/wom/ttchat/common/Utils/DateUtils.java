package com.wom.ttchat.common.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String dateFormatting(String date) {
            // 문자열을 LocalDateTime 객체로 파싱
        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);

            // 월과 일만 추출하여 문자열로 가공
        String formattedDate = dateTime.format(DateTimeFormatter.ofPattern("MM-dd"));
        return formattedDate;
    }

}
