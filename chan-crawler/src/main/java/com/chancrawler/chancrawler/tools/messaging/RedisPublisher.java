package com.chancrawler.chancrawler.tools.messaging;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;

public class RedisPublisher {
    private final String client;
    private final RedisClient redisClient;
    private final StatefulRedisConnection<String, String> redisSender;

    public RedisPublisher(String ip, int port) {
        this.client = "redis://" + ip + ":" + Integer.toString(port) + "/";
        this.redisClient = RedisClient.create(this.client);
        this.redisSender = this.redisClient.connect();
    }

    public String getClientInfo() {
        return client;
    }

    public void publish(String channel, String message) {
        redisSender.async().publish(channel, message);
        return;
    }
}
