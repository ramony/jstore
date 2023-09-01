package com.raybyte.jstore.controller;

import com.raybyte.jstore.entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingController {

    @RequestMapping("")
    public String index() {
        return "pong";
    }

}
