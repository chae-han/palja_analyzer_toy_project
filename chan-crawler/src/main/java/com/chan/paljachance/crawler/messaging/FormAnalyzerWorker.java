package com.chan.paljachance.crawler.messaging;


//
//public class FormAnalyzerWorker<K,V,T extends RedisTask> extends RedisWorker<K,V,T> {
//    private static final Logger logger = Logger.getLogger(FormAnalyzerWorker.class.getName());
//
//    public FormAnalyzerWorker(Class<T> taskClass, final int MaxWorkerNumber) {
//        super(taskClass, MaxWorkerNumber);
//    }
//
//    @Override
//    void workerHandler(K channel, V message) {
//        logger.info("[workerHandler start]========== " + channel + ", " + message);
//
//        while(true) {
//            for (int i = 0; i < maxWorkerNumber; i++) {
//                if (workers.get(i) == null || workers.get(i).getState() == Thread.State.NEW  || workers.get(i).getState() == Thread.State.TERMINATED) {
//                    try {
//                        workers.set(i, (T) taskClass.getDeclaredConstructor(String.class, String.class).newInstance(channel, message));
//                        workers.get(i).start();
//                        return;
//                    } catch (Exception e) {
//                        throw new RuntimeException("instance cannot created");
//                    }
//                }
//            }
//        }
//    }
//}