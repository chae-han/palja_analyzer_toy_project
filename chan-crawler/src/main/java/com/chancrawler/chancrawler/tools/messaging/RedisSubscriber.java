package com.chancrawler.chancrawler.tools.messaging;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.pubsub.RedisPubSubAdapter;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;

public class RedisSubscriber {
    private final String client;
    private final RedisClient redisClient;
    private final StatefulRedisPubSubConnection<String, String> redisPubSubConnection;

    public RedisSubscriber(String ip, int port, final String... channel) {
        this.client = "redis://" + ip + ":" + Integer.toString(port) + "/";
        this.redisClient = RedisClient.create(this.client);
        this.redisPubSubConnection = this.redisClient.connectPubSub();
        this.redisPubSubConnection.async().subscribe(channel);
    }

    public String getClientInfo() {
        return client;
    }

    public void addListener(final RedisPubSubAdapter redisPubSubAdapter) {
        this.redisPubSubConnection.addListener(redisPubSubAdapter);
        return;
    }

}
