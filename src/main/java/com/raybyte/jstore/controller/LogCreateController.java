package com.raybyte.jstore.controller;

import com.raybyte.jstore.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/log")
public class LogCreateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogCreateController.class);

    @RequestMapping("/create")
    public Result create() {
        float rand = new Random().nextInt(100);
        if (rand < 25) {
            LOGGER.info("info:" + rand);
        } else if (rand < 50) {
            LOGGER.warn("warn:" + rand);
        } else if (rand < 75) {
            LOGGER.error("error:" + rand);
        } else {
            throw new RuntimeException("jstore run exception");
        }
        return Result.ok(rand);
    }

}
