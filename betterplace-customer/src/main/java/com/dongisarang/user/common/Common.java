package com.dongisarang.user.common;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Common {

    public String commentDateFormat(Date date){

        String result = "";
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
        result = outputDateFormat.format(date);

        return result;
    }
}
