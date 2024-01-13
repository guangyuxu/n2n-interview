package com.n2n.application.alien;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Configuration
public class AlienClockConfig {

    public static final List<Integer> MONTHLY_DAYS = Arrays
            .asList(0, 44, 42, 48, 40, 48, 44, 40, 44, 42, 40, 40, 42, 44, 48, 42, 40, 44, 38);

    private static final List<Integer> MONTHLY_SUM_DAYS = IntStream
            .range(0, MONTHLY_DAYS.size())
            .mapToObj(i -> MONTHLY_DAYS.subList(0, i + 1).stream().mapToInt(Integer::intValue).sum())
            .toList();

    public static final int YEARLY_MONTH_COUNT = 18;
    public static final long YEARLY_DAY_COUNT = 770L;

    public static final int DAILY_HOUR_COUNT = 36;

    public static final int HOURLY_MINUTE_COUNT = 90;

    public static final int MINUTELY_SECOND_COUNT = 90;

    @Bean
    public List<Integer> monthlySumDays() {
        return new ArrayList<>(MONTHLY_SUM_DAYS);
    }
}

