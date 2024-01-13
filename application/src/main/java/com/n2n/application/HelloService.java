package com.n2n.application;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String hello(){
        return "Hello N2N Service";
    }
}
