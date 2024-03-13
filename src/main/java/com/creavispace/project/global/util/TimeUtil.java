package com.creavispace.project.global.util;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Getter
@Service
public class TimeUtil {

    public LocalDateTime getCurrentLocalDate() {
        Date date = new Date();
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static int getUntilMidnight(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime midnight = now.toLocalDate().atTime(LocalTime.MAX).plusDays(1);
        return (int) now.until(midnight, ChronoUnit.SECONDS);
    }

    public static LocalDate getRecruitEnd(String end, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(end, formatter);
    }
}
