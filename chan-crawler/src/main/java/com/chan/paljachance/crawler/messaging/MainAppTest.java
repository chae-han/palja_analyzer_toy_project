package com.chan.paljachance.crawler.messaging;

import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


//https://www.baeldung.com/spring-boot-xml-beans 참
@Configuration
@EnableAutoConfiguration
//@ImportResource("classpath:AppConfig.xml")
public class MainAppTest {
    private static final Logger logger = Logger.getLogger(MainAppTest.class.getName());
    private final RedisPublisher redisPublisher;
    private final RedisSubscriber redisSubscriber;
    private int workerNumber = 5;

    //    private RedisPublisher redisPublisher;
    MainAppTest(final RedisPublisher redisPublisher, final RedisSubscriber redisSubscriber) {
        this.redisPublisher = redisPublisher;
        this.redisSubscriber = redisSubscriber;
    }

    public void setWorkerNumber(final int workerNumber) {
        this.workerNumber = workerNumber;
    }

    public int getWorkerNumber() {
        return this.workerNumber;
    }

    @Scheduled(fixedRate=3000000)
    public void startTest() {
        logger.info("MainAppTest's startTest() start =========================");

        if (workerNumber == 0) {
            return;
        }
        //final RedisPublisher redisPublisher = new RedisPublisher();
        //StatefulRedisConnection<String, String> sender = redisPublisher.connectToRedis("localhost", 6379);

        StatefulRedisPubSubConnection<String, String> pageParserSubscriber = redisSubscriber.getNewSubscriber("ch01");
        //StatefulRedisPubSubConnection<String, String> formAnalyzerSubscriber = redisSubscriber.getNewSubscriber("ch02");

        List<PageParserTask> pageParserTasks = new ArrayList<>();
        System.out.println(workerNumber);

        for (int i = 0; i < workerNumber; i++) {
            pageParserTasks.add(null);
        }

        final RedisWorker pageParserWorker = new PageParserWorker<String,String, PageParserTask>(PageParserTask.class, pageParserTasks, redisPublisher);
        //final RedisWorker formAnalyzerWorker = new FormAnalyzerWorker<String,String, FormAnalyzerTask>(FormAnalyzerTask.class, 2);

        redisSubscriber.addListener(pageParserSubscriber, pageParserWorker);
        //redisSubscriber.addListener(formAnalyzerSubscriber, formAnalyzerWorker);

        for(int i = 0; i< 50; i++) {
            redisPublisher.publish("ch01", "message-" + Integer.toString(i));
        }
    }

}
