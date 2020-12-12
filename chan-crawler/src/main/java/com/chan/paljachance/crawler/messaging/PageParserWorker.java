package com.chan.paljachance.crawler.messaging;

import java.util.List;
import java.util.logging.Logger;


public class PageParserWorker<K,V,T extends RedisTask> extends RedisWorker<K,V,T> {

    private static Logger logger = Logger.getLogger(PageParserWorker.class.getName());
    private final RedisPublisher redisPublisher;
    private final Class<T> taskClass;

    public PageParserWorker(final Class<T> taskClass, final List<T> workers, final RedisPublisher redisPublisher) {
        super(workers);
        this.redisPublisher = redisPublisher;
        this.taskClass = taskClass;
    }

    @Override
    void workerHandler(K channel, V message) {
        logger.info("[workerHandler start]========== " + channel + ", " + message);

        while(true) {
            for (int i = 0; i < maxWorkerNumber; i++) {

                if (workers.get(i) == null || workers.get(i).getState() == Thread.State.NEW  || workers.get(i).getState() == Thread.State.TERMINATED) {

                    try {
                        //workers.set(i, (T) taskClass.getDeclaredConstructor(String.class, String.class, RedisPublisher.class).newInstance(channel, message, redisPublisher));
                        workers.set(i, (T) taskClass.getDeclaredConstructor(String.class, String.class, RedisPublisher.class).newInstance(channel, message, redisPublisher));
                        workers.get(i).start();
                        return;
                    } catch (Exception e) {
                        throw new RuntimeException("instance cannot created");
                    }
                }
            }
        }
    }

//    private static Logger logger = Logger.getLogger(PageParserWorker.class.getName());
//    private final RedisPublisher redisPublisher;
//
//    public PageParserWorker(Class<T> taskClass, final int MaxWorkerNumber, final RedisPublisher redisPublisher) {
//        super(taskClass, MaxWorkerNumber);
//        this.redisPublisher = redisPublisher;
//    }
//
//    @Override
//    void workerHandler(K channel, V message) {
//        logger.info("[workerHandler start]========== " + channel + ", " + message);
//
//        while(true) {
//            for (int i = 0; i < maxWorkerNumber; i++) {
//
//                if (workers.get(i) == null || workers.get(i).getState() == Thread.State.NEW  || workers.get(i).getState() == Thread.State.TERMINATED) {
//
//                    try {
//                        workers.set(i, (T) taskClass.getDeclaredConstructor(String.class, String.class, RedisPublisher.class).newInstance(channel, message, redisPublisher));
//                        workers.get(i).start();
//                        return;
//                    } catch (Exception e) {
//                        throw new RuntimeException("instance cannot created");
//                    }
//                }
//            }
//        }
//    }

}
