package com.chancrawler.chancrawler.tools.messaging;

abstract class RedisTask extends Thread{

    protected String message = "";
    protected String channel = "";

    public RedisTask(final String channel, final String message) {
        this.message = message;
        this.channel = channel;
    }

    @Override
    public abstract void run();
}

//
//public class ParserTask extends Thread{
//
//    private String message = "";
//    private String channel = "";
//    private RedisPublisher redisPublisher = null;
//
//    public ParserTask(final String channel, final String message, final RedisPublisher redisPublisher) {
//        this.message = message;
//        this.channel = channel;
//        this.redisPublisher = redisPublisher;
//    }
//
//    @Override
//    public void run() {
//        System.out.println(message);
//        try {
//            String[] arr = message.split("-");
//            int val = Integer.parseInt(arr[1]);
//            if(val%2 == 0) {
//                Thread.sleep(3000);
//                System.out.println("done");
//            } else {
//                Thread.sleep(1000);
//                System.out.println("retry");
//                redisPublisher.publish(channel, "message-"+Integer.toString(val-1));
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//}
