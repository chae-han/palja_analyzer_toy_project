package com.chancrawler.chancrawler.libTestExamples.redis;

import com.chancrawler.chancrawler.RunCrawlerApplication;
import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.RedisPubSubAdapter;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;

import java.util.logging.Logger;

public class subscriber {
    private static final Logger logger = Logger.getLogger(subscriber.class.getName());

    public static void main(String[] args) {

        final int MAX_THREAD_NUM = 3;
        final RedisClient client = RedisClient.create("redis://localhost:6379/");
        StatefulRedisPubSubConnection<String, String> con = client.connectPubSub();

        RedisPubSubAsyncCommands<String, String> async = con.async();
        async.subscribe("ch01");

        con.addListener(new RedisPubSubAdapter<String, String>() {
            ThreadPoolSample[] runnable = {null, null, null};

            @Override
            public void message(String channel, String message) {
                logger.info("handler start with channel "+ channel + " message is " + message);
                while(true) {
                    for (int i = 0; i < MAX_THREAD_NUM; i++) {
                        if (runnable[i] == null || runnable[i].getState() == Thread.State.NEW  || runnable[i].getState() == Thread.State.TERMINATED) {
                            runnable[i] = new ThreadPoolSample(message);
                            runnable[i].start();
                            return;
                        }
                    }
                }
            }
        });

//            final int MAX_THREAD_NUM = 3;
//            final RedisClient client = RedisClient.create("redis://localhost:6379/");
//            StatefulRedisPubSubConnection<String, String> con = client.connectPubSub();
//
//            RedisPubSubAsyncCommands<String, String> async = con.async();
//            async.subscribe("ch01");
//
//        con.addListener(new RedisPubSubAdapter<String, String>() {
//            ThreadPoolSample[] runnable = {null, null, null};
//
//            @Override
//            public void message(String channel, String message) {
//
//                while(true) {
//                    for (int i = 0; i < MAX_THREAD_NUM; i++) {
//                        if (runnable[i] == null || runnable[i].getState() == Thread.State.NEW  || runnable[i].getState() == Thread.State.TERMINATED) {
//                            runnable[i] = new ThreadPoolSample(message);
//                            runnable[i].start();
//                            return;
//                        }
//                    }
//                }
//            }
//        });

    }

}


