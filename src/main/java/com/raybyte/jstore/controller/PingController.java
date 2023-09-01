package com.raybyte.jstore.controller;

import com.raybyte.jstore.entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PingController {

    @RequestMapping("/ping")
    public String index() {
        return "pong";
    }

}
