package com.chancrawler.chancrawler;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Order(1)
public class RunCrawlerApplication implements ApplicationRunner {
    private static Logger logger = Logger.getLogger(RunCrawlerApplication.class.getName());

    @Override
    public void run(ApplicationArguments arguments) throws Exception{
        logger.info("start");


    }
}




