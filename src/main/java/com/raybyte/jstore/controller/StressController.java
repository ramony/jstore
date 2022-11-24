package com.raybyte.jstore.controller;

import com.raybyte.jstore.entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stress")
public class StressController {

    @RequestMapping("/bigTask")
    public Result bigTask() {

        for (int i = 1; i < 10000; i++) {
            new Object();
        }
        try {
            Thread.sleep((long) (Math.random() * 2000));
        } catch (InterruptedException e) {

        }
        return Result.ok("OK");
    }

}
