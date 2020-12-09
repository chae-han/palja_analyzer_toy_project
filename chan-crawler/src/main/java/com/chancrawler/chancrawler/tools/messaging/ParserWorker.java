package com.chancrawler.chancrawler.tools.messaging;

import java.util.List;

public class ParserWorker<K,V,T extends Thread> extends RedisListener<K,V,T>{

    private final int maxWorkerNumber;
    private final List<T> workers;
    private final Class<T> taskClass;

    public ParserWorker(Class<T> taskClass, final int MaxWorkerNumber) {
        super(taskClass, MaxWorkerNumber);
        this.maxWorkerNumber = getMaxWorkerNumber();
        this.workers = getWorkers();
        this.taskClass = getTaskClass();
    }

    @Override
    void listenerHandler(K channel, V message) {
        while(true) {
            for (int i = 0; i < maxWorkerNumber; i++) {
                if (workers.get(i) == null || workers.get(i).getState() == Thread.State.NEW  || workers.get(i).getState() == Thread.State.TERMINATED) {
                    try {
                        //Class.getDeclaredConstructor(String.class).newInstance("HERESMYARG");
                        workers.set(i, (T) taskClass.getDeclaredConstructor(String.class).newInstance(message));
                        workers.get(i).start();
                        return;
                    } catch (Exception e) {
                        throw new RuntimeException("instance cannot created");
                    }
                }
            }
        }
    }
}
