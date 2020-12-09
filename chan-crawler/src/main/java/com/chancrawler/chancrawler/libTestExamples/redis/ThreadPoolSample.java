package com.chancrawler.chancrawler.libTestExamples.redis;

public class ThreadPoolSample extends Thread{
    //private final String threadName;
    private String message = "";

    public ThreadPoolSample(String threadName) {
        this.message = threadName;
    }

    @Override
    public void run() {
        System.out.println(message);
        try {
            Thread.sleep(3000);
            System.out.println("done");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
