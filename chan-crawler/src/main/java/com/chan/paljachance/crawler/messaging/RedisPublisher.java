package com.chan.paljachance.crawler.messaging;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisPublisher {

    //private static StatefulRedisConnection<String, String> redisSender;

    @Bean
    StatefulRedisConnection<String, String> redisPublisherConnection() {
        RedisClient redisClient = RedisClient.create("redis://localhost:6379/");
        StatefulRedisConnection<String, String> redisSender = redisClient.connect();
        return redisSender;
    }

//    public StatefulRedisConnection<String, String> connectToRedis(String ip, int port) {
//        RedisClient redisClient = RedisClient.create("redis://" + ip + ":" + Integer.toString(port) + "/");
//        StatefulRedisConnection<String, String> redisSender = redisClient.connect();
//
//        return redisSender;
//    }

    public void publish(String channel, String message) {
        redisPublisherConnection().async().publish(channel, message);
        return;
    }
}
