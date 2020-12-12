package com.chan.paljachance.crawler.libTestExamples.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;

public class publisher {
    public static void main(String[] args) {
        RedisClient client = RedisClient.create("redis://localhost:6379/");

        StatefulRedisConnection<String, String> sender = client.connect();
        int i = 0;
        for (i = 0; i< 100; i++) {
            sender.async().publish("ch01", "Message " + Integer.toString(i));
        }
    }
}
