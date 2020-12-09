package com.chancrawler.chancrawler.tools.messaging;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


public class MainAppTest {

    @Scheduled(fixedDelay=180000)
    public void startTest() {
        final RedisPublisher redisPublisher = new RedisPublisher("localhost", 6379);

        final RedisSubscriber pageParserSubscriber = new RedisSubscriber("localhost", 6379, "ch01");
        final RedisWorker pageParserWorker = new PageParserWorker<String,String, PageParserTask>(PageParserTask.class, 5, redisPublisher);

        final RedisSubscriber formAnalyzerSubscriber = new RedisSubscriber("localhost", 6379, "ch02");
        final RedisWorker formAnalyzerWorker = new FormAnalyzerWorker<String,String, FormAnalyzerTask>(FormAnalyzerTask.class, 2);

        pageParserSubscriber.addListener(pageParserWorker);
        formAnalyzerSubscriber.addListener(formAnalyzerWorker);

        for(int i = 0; i< 50; i++) {
            redisPublisher.publish("ch01", "message-" + Integer.toString(i));
        }
    }
}
