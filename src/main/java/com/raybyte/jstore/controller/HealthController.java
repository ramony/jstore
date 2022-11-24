package com.raybyte.jstore.controller;

import com.raybyte.jstore.entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    @RequestMapping("/status")
    public Result status() {
        return Result.ok("OK");
    }

}
