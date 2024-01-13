package com.n2n.application.alien;

import org.springframework.util.Assert;

public class AlienClock extends CommonClock {
    private static final int[] MONTHLY_DAYS = new int[]{0, 44, 42, 48, 40, 48, 44, 40, 44, 42, 40, 40, 42, 44, 48, 42, 40, 44, 38};
    public AlienClock(int year, int month, int day, int hour, int minute, int second) {
        super(year, month, day, hour, minute, second);
        Assert.isTrue(0 <= year, "year: [0,)");
        Assert.isTrue (0 < month && month <= 18, "month: [1, 18]");
        Assert.isTrue (0 < day && day <= MONTHLY_DAYS[month], "day: [1, " + MONTHLY_DAYS[month] + "]");
        Assert.isTrue (0 <= hour && hour < 36, "hour: [0,36)");
        Assert.isTrue (0 <= minute && minute < 90, "minute: [0,90)");
        Assert.isTrue (0 <= second && second < 90, "second: [0,90)");
    }
}
