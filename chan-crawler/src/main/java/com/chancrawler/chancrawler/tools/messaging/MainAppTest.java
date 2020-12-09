package com.chancrawler.chancrawler.tools.messaging;

public class MainAppTest {
    public static void main(String[] args) {
        final RedisPublisher redisPublisher = new RedisPublisher("localhost", 6379);
        final RedisSubscriber redisSubscriber = new RedisSubscriber("localhost", 6379, "ch01");
        final ParserWorker<String,String,ThreadPoolSample> parserWorker = new ParserWorker<>(ThreadPoolSample.class, 5);

        redisSubscriber.addListener(parserWorker);

        for(int i = 0; i< 10000; i++) {
            redisPublisher.publish("ch01", "message - " + Integer.toString(i));
        }
    }
}
