package com.n2n.api;

import com.n2n.application.alien.CommonClock;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;

/*
 * The cross-origin setup is provided only for local project
 */
@CrossOrigin
@RestController
@RequestMapping("/earth-clock")
@RequiredArgsConstructor
public class EarthController {
    @GetMapping("/now")
    public CommonClock now() {
        LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("UTC"));
        return new CommonClock(
                dateTime.getYear(),
                dateTime.getMonthValue(),
                dateTime.getDayOfMonth(),
                dateTime.getHour(),
                dateTime.getMinute(),
                dateTime.getSecond());
    }
}
