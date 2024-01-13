package com.n2n.application.alien;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.List;

import static com.n2n.application.alien.AlienClockConfig.*;

@Service
@RequiredArgsConstructor
public class AlienClockServiceImpl implements AlienClockService {

    private final List<Integer> monthlySumDays;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public AlienClock valid(CommonClock ac) {
        return new AlienClock(ac.getYear(), ac.getMonth(), ac.getDay(), ac.getHour(), ac.getMinute(), ac.getSecond());
    }

    @Override
    public CommonClock getCurrentAlienClock() {
        long alienTime = earthTimeToAlienTime(System.currentTimeMillis());
        return alienTimeToClock(alienTime);
    }

    @Override
    public CommonClock getEarthClockFromAlienClock(CommonClock commonClock) {
        AlienClock alienClock = valid(commonClock);
        long alienTime = alienClockToTime(alienClock);
        long earthTime = alienTimeToEarthTime(alienTime);
        ZonedDateTime d = ZonedDateTime.ofInstant(Instant.ofEpochMilli(earthTime), ZoneId.of("UTC"));
        return new CommonClock(d.getYear(), d.getMonth().getValue(), d.getDayOfMonth(), d.getHour(), d.getMinute(), d.getSecond());
    }

    @Override
    public CommonClock getAlienClockFromEarthClock(CommonClock earthClock) {
        ZoneId zoneId = ZoneId.of("UTC");
        ZonedDateTime earthDateTime = ZonedDateTime.of(earthClock.getYear(),
                earthClock.getMonth(),
                earthClock.getDay(),
                earthClock.getHour(),
                earthClock.getMinute(),
                earthClock.getSecond(),
                0, zoneId);
        long epochMillis = earthDateTime.toInstant().toEpochMilli();
        long alienTime = earthTimeToAlienTime(epochMillis);
        return alienTimeToClock(alienTime);
    }

    private long earthTimeToAlienTime(long earthTime) {
        return earthTime / 500 + alienClockOffset();
    }

    private long alienTimeToEarthTime(long alienTime) {
        return (alienTime - alienClockOffset()) * 500;
    }

    private long alienClockOffset() {
        long offset = alienClockToTime(new AlienClock(2804, 18, 31, 2, 2, 88));
        return offset;
    }

    private AlienClock alienTimeToClock(long time) {
        // 1 minute is 90 seconds.
        int second = (int) (time % MINUTELY_SECOND_COUNT);
        time = time / MINUTELY_SECOND_COUNT;
        // 1 hour is 90 minutes.
        int minute = (int) (time % HOURLY_MINUTE_COUNT);
        time = time / HOURLY_MINUTE_COUNT;
        // 1 day is 36 hours.
        int hour = (int) (time % DAILY_HOUR_COUNT);
        time = time / DAILY_HOUR_COUNT;

        int day = (int) (time % YEARLY_DAY_COUNT) + 1;
        int month = 0;
        for (int i = 0; i <= YEARLY_MONTH_COUNT; i++) {
            if (day < monthlySumDays.get(i)) {
                month = i;
                day = day - monthlySumDays.get(i - 1);
                break;
            }
        }
        // 1 year is 770 days
        int year = (int) (time / YEARLY_DAY_COUNT);
        return new AlienClock(year, month, day, hour, minute, second);
    }

    private long alienClockToTime(AlienClock alienClock) {
        int year = alienClock.getYear();
        int month = alienClock.getMonth();
        int day = alienClock.getDay();
        int hour = alienClock.getHour();
        int minute = alienClock.getMinute();
        int second = alienClock.getSecond();
        assert monthlySumDays != null;
        long countDays = year * YEARLY_DAY_COUNT + monthlySumDays.get(month - 1) + day - 1;
        long countHours = countDays * DAILY_HOUR_COUNT + hour;
        long countMinutes = countHours * HOURLY_MINUTE_COUNT + minute;
        long countSeconds = countMinutes * MINUTELY_SECOND_COUNT + second;
        return countSeconds;
    }
}
