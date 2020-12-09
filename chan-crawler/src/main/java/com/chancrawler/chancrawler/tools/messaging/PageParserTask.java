package com.chancrawler.chancrawler.tools.messaging;

import com.chancrawler.chancrawler.RunCrawlerApplication;

import java.util.logging.Logger;

import static com.chancrawler.chancrawler.tools.parser.PageParser.*;

public class PageParserTask extends RedisTask{
    private static Logger logger = Logger.getLogger(PageParserTask.class.getName());
    private RedisPublisher redisPublisher = null;

    public PageParserTask(final String channel, final String message, final RedisPublisher redisPublisher) {
        super(channel, message);
        this.redisPublisher = redisPublisher;
    }

    @Override
    public void run() {
        logger.info(channel+ " start with message " + message);

        try {
            String[] arr = message.split("-");
            int val = Integer.parseInt(arr[1]);

            boolean result = parsing(arr[1]);

            if(result) {
                logger.info(channel+ " send message to ch02");
                redisPublisher.publish("ch02", "message-"+Integer.toString(val-1));
            }else {
                Thread.sleep(5000);
                logger.info(channel+ " work done");
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
