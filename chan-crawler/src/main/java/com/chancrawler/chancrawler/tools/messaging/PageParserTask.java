package com.chancrawler.chancrawler.tools.messaging;

import static com.chancrawler.chancrawler.tools.parser.PageParser.*;

public class PageParserTask extends RedisTask{

    private RedisPublisher redisPublisher = null;

    public PageParserTask(final String channel, final String message, final RedisPublisher redisPublisher) {
        super(channel, message);
        this.redisPublisher = redisPublisher;
    }

    @Override
    public void run() {
        System.out.println(channel + "==" + message);

        try {
            String[] arr = message.split("-");
            int val = Integer.parseInt(arr[1]);

            boolean result = parsing(arr[1]);

            if(result) {
                System.out.println(channel + "==" + "retry");
                redisPublisher.publish("ch02", "message-"+Integer.toString(val-1));
            }else {
                Thread.sleep(5000);
                System.out.println(channel + "==" + "done");
                //redisPublisher.publish(channel, "message-"+Integer.toString(val-1));
            }

//            int val = Integer.parseInt(arr[1]);
//            if(val%2 == 0) {
//                Thread.sleep(3000);
//                System.out.println("done");
//            } else {
//                Thread.sleep(1000);
//                System.out.println("retry");
//                redisPublisher.publish(channel, "message-"+Integer.toString(val-1));
//            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
