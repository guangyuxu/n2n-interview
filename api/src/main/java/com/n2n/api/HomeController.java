package com.n2n.api;

import com.n2n.application.HelloService;
import com.n2n.application.alien.CommonClock;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * The cross-origin setup is provided only for local project
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class HomeController {

    private final HelloService helloService;

    @GetMapping("/hello")
    public String hello(){
        return helloService.hello();
    }
}
