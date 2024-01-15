package com.creavispace.project.global.util;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Getter
@Service
public class TimeUtil {

    public LocalDateTime getCurrentLocalDate() {
        Date date = new Date();
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
