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

    abstract void listenerHandler(K channel, V message);

//    public int getMaxWorkerNumber() {
//        return MaxWorkerNumber;
//    }
//
//    public List<T> getWorkers() {
//        return workers;
//    }
//
//    public Class<T> getTaskClass() {
//        return taskClass;
//    }

    @Override
    public void message(K channel, V message) {
        listenerHandler(channel, message);
    }

}
