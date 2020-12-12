package com.chan.paljachance.crawler.libTestExamples.rabbit;

public class ThreadJob {
    private final String message;
    ThreadJob(String msg){
        this.message = msg;
    }

    public void testFunc() throws InterruptedException {
        System.out.println(message);
        Thread.sleep(1000);
    }
}
