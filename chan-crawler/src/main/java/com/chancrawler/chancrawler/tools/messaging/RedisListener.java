package com.chancrawler.chancrawler.tools.messaging;

import io.lettuce.core.pubsub.RedisPubSubAdapter;

import java.util.ArrayList;
import java.util.List;

abstract class RedisListener<K,V,T extends Thread> extends RedisPubSubAdapter<K,V> {

    private final int MaxWorkerNumber;
    private final List<T> workers = new ArrayList<>();
    private final Class<T> taskClass;

    public RedisListener(Class<T> taskClass, final int MaxWorkerNumber) {
        this.MaxWorkerNumber = MaxWorkerNumber;
        this.taskClass = taskClass;

        for (int i = 0; i < MaxWorkerNumber; i++) {
            this.workers.add(null);
        }
    }


    abstract void listenerHandler(K channel, V message);

    public int getMaxWorkerNumber() {
        return MaxWorkerNumber;
    }

    public List<T> getWorkers() {
        return workers;
    }

    public Class<T> getTaskClass() {
        return taskClass;
    }

    @Override
    public void message(K channel, V message) {
        listenerHandler(channel, message);
    }

}
