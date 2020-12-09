package com.chancrawler.chancrawler.tools.messaging;

import io.lettuce.core.pubsub.RedisPubSubAdapter;

import java.util.ArrayList;
import java.util.List;

abstract class RedisWorker<K,V,T extends Thread> extends RedisPubSubAdapter<K,V> {

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
        workerHandler(channel, message);
    }

}
