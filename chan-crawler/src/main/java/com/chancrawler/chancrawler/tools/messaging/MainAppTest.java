package com.chancrawler.chancrawler.tools.messaging;

public class MainAppTest {
    public static void main(String[] args) {
        final RedisPublisher redisPublisher = new RedisPublisher("localhost", 6379);
        final RedisSubscriber redisSubscriber = new RedisSubscriber("localhost", 6379, "ch01");
        final PageParserWorker<String,String, PageParserTask> pageParserWorker = new PageParserWorker<>(PageParserTask.class, 5, redisPublisher);

        redisSubscriber.addListener(pageParserWorker);

        for(int i = 0; i< 50; i++) {
            redisPublisher.publish("ch01", "message-" + Integer.toString(i));
        }
    }
}
