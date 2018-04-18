package com.datnguyen.socialnetwork.restapi;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

    public static void main(String[] args){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(timestamp.getTime());

        // S is the millisecond
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
        System.out.println(timestamp.toString());
        System.out.println(simpleDateFormat.format(timestamp));
        System.out.println(simpleDateFormat.format(date));
        
        Long l = Long.parseLong("1426961332000");
        Date date1 = new Date(l);
        System.out.println(simpleDateFormat.format(date1));
    }
}