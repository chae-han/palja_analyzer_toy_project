package com.chancrawler.chancrawler.tools.messaging;

import com.chancrawler.chancrawler.RunCrawlerApplication;
import com.chancrawler.chancrawler.libTestExamples.redis.ThreadPoolSample;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.pubsub.RedisPubSubAdapter;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


@Component
public class MainAppTest {
    private static final Logger logger = Logger.getLogger(MainAppTest.class.getName());

    @Autowired
    private RedisPublisher redisPublisher;

    @Scheduled(fixedRate=3000000)
    public void startTest() {
        logger.info("MainAppTest's startTest() start =========================");
        //final RedisPublisher redisPublisher = new RedisPublisher();
        //StatefulRedisConnection<String, String> sender = redisPublisher.connectToRedis("localhost", 6379);

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
