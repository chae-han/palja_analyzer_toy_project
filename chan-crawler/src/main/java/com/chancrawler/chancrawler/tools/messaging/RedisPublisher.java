package com.chancrawler.chancrawler.tools.messaging;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.stereotype.Component;

@Component
public class RedisPublisher {

    private static StatefulRedisConnection<String, String> redisSender;

    RedisPublisher() {
        RedisClient redisClient = RedisClient.create("redis://localhost:6379/");
        this.redisSender = redisClient.connect();
    }

//    public StatefulRedisConnection<String, String> connectToRedis(String ip, int port) {
//        RedisClient redisClient = RedisClient.create("redis://" + ip + ":" + Integer.toString(port) + "/");
//        StatefulRedisConnection<String, String> redisSender = redisClient.connect();
//
//        return redisSender;
//    }

    public void publish(String channel, String message) {
        redisSender.async().publish(channel, message);
        return;
    }
}
