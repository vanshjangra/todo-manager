package com.lcwd.todo.helper;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Helper {
    public static Date parseDate(LocalDateTime localDateTime) throws ParseException {
        System.out.println(localDateTime);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        return date;
    }
}
