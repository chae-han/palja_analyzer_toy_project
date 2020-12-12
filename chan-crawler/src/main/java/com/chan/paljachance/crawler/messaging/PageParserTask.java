package com.chan.paljachance.crawler.messaging;

import static com.chancrawler.chancrawler.tools.parser.PageParser.*;

import java.util.logging.Logger;


public class PageParserTask extends RedisTask{
    private static final Logger logger = Logger.getLogger(PageParserTask.class.getName());
    private final RedisPublisher redisPublisher;

    public PageParserTask(final String channel, final String message, final RedisPublisher redisPublisher) {
        super(channel, message);
        this.redisPublisher = redisPublisher;
    }

    @Override
    public void run() {

        logger.info("[PageParserTask start]========== " + channel + ", " + message);

        try {

            String[] arr = message.split("-");
            int val = Integer.parseInt(arr[1]);

            boolean result = parsing(arr[1]);

            if(result) {
                redisPublisher.publish("ch02", "message-"+Integer.toString(val-1));
            }else {
                Thread.sleep(5000);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
