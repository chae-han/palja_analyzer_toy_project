package com.chancrawler.chancrawler;


import com.chancrawler.chancrawler.tools.messaging.*;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.pubsub.RedisPubSubAdapter;
import io.lettuce.core.pubsub.RedisPubSubListener;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
@Order(1)
public class RunCrawlerApplication implements ApplicationRunner {
    private static Logger logger = Logger.getLogger(RunCrawlerApplication.class.getName());

//    @Autowired
//    private MainAppTest mainAppTest;

    @Override
    public void run(ApplicationArguments arguments) throws Exception{
        logger.info("start");

//        MainAppTest mainAppTest = new MainAppTest();
        //mainAppTest.startTest();
    }
}




