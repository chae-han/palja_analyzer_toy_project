package com.chan.paljachance.crawler.messaging;

import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.RedisPubSubAdapter;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisSubscriber {
//    private final String client;
//    private final RedisClient redisClient;
//    private final StatefulRedisPubSubConnection<String, String> redisPubSubConnection;

    @Bean
    public RedisClient redisSubscriberConnection() {//(String ip, int port, final String... channel) {
//        this.client = "redis://" + ip + ":" + Integer.toString(port) + "/";
//        this.redisClient = RedisClient.create(this.client);
//        this.redisPubSubConnection = this.redisClient.connectPubSub();
//        this.redisPubSubConnection.async().subscribe(channel);
        RedisClient redisClient = RedisClient.create("redis://localhost:6379/");
        //StatefulRedisPubSubConnection<String, String> redisPubSubConnection = redisClient.connectPubSub();

        return redisClient;
    }

    public StatefulRedisPubSubConnection<String, String> getNewSubscriber(final String... channel) {
        StatefulRedisPubSubConnection<String, String> redisPubSubConnection = redisSubscriberConnection().connectPubSub();
        redisPubSubConnection.async().subscribe(channel);
        return redisPubSubConnection;
    }

//    public String getClientInfo() {
//        return client;
//    }

    public void addListener(StatefulRedisPubSubConnection<String, String> subscriber, final RedisPubSubAdapter redisPubSubAdapter) {
        //redisConnection().async().subscribe(channel);
        subscriber.addListener(redisPubSubAdapter);
        return;
    }

}
