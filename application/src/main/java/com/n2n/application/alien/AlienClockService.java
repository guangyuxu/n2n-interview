package com.n2n.application.alien;

import org.springframework.stereotype.Service;


public interface AlienClockService {
    AlienClock valid(CommonClock alienClock);

    CommonClock getCurrentAlienClock();

    CommonClock getEarthClockFromAlienClock(CommonClock alienClock);

    CommonClock getAlienClockFromEarthClock(CommonClock earthClock);
}
