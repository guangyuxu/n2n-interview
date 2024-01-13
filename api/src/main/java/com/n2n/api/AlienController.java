package com.n2n.api;

import com.n2n.application.alien.AlienClock;
import com.n2n.application.alien.AlienClockService;
import com.n2n.application.alien.CommonClock;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/*
 * The cross-origin setup is provided only for local project
 */
@CrossOrigin
@RestController
@RequestMapping("/alien-clock")
@RequiredArgsConstructor
public class AlienController {

    private final AlienClockService service;

    @PostMapping("/valid")
    @ResponseBody
    public AlienClock validAlienClock(@RequestBody CommonClock clock){
        return service.valid(clock);
    }

    @GetMapping("/now")
    public CommonClock now() {
        return service.getCurrentAlienClock();
    }

    @PostMapping("/alien-to-earth")
    public CommonClock getEarthClockFromAlienClock(@RequestBody CommonClock alienClock) {
        return service.getEarthClockFromAlienClock(alienClock);
    }

    @PostMapping("/earth-to-alien")
    public CommonClock getAlienClockFromEarthClock(@RequestBody CommonClock earthClock) {
        return service.getAlienClockFromEarthClock(earthClock);
    }
}
