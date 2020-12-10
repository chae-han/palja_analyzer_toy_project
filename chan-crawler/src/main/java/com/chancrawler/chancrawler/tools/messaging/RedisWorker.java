package com.chancrawler.chancrawler.tools.messaging;

import io.lettuce.core.pubsub.RedisPubSubAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class RedisWorker<K,V,T extends Thread> extends RedisPubSubAdapter<K,V> {

    private static final Logger logger = Logger.getLogger(RedisWorker.class.getName());

    protected final int maxWorkerNumber;
    protected final List<T> workers = new ArrayList<>();
    protected final Class<T> taskClass;

    public RedisWorker(Class<T> taskClass, final int maxWorkerNumber) {
        this.maxWorkerNumber = maxWorkerNumber;
        this.taskClass = taskClass;

        for (int i = 0; i < maxWorkerNumber; i++) {
            this.workers.add(null);
        }
    }

    abstract void workerHandler(K channel, V message);

    @Override
    public void message(K channel, V message) {
        logger.info("[RedisWorker start]========== " + channel + ", " + message);
        workerHandler(channel, message);
    }

}
