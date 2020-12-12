package com.chan.paljachance;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Order(1)
public class RunCrawlerApplication implements ApplicationRunner {
    private static Logger logger = Logger.getLogger(RunCrawlerApplication.class.getName());
//
//    @Autowired
//    private MainAppTest mainAppTest;

    @Override
    public void run(ApplicationArguments arguments) throws Exception{
        logger.info("start");

////        MainAppTest mainAppTest = new MainAppTest();
//        mainAppTest.startTest();
    }
}




