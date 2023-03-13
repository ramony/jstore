package com.raybyte.jstore.controller;

import com.raybyte.jstore.entity.Result;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/log")
public class LogCreateController {

    Logger logger = Logger.getLogger(LogCreateController.class);

    @RequestMapping("/create")
    public Result create() {
        float rand = new Random().nextInt(100);
        if (rand < 33) {
            logger.info("info:" + rand);
        } else if (rand < 66) {
            logger.warn("warn:" + rand);
        } else {
            logger.error("error:" + rand);
        }
        return Result.ok(rand);
    }

}