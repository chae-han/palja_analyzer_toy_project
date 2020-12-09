package com.chancrawler.chancrawler.tools.messaging;

import java.util.logging.Logger;

import static com.chancrawler.chancrawler.tools.parser.PageParser.parsing;

public class FormAnalyzerTask extends RedisTask{
    private static Logger logger = Logger.getLogger(FormAnalyzerTask.class.getName());
    public FormAnalyzerTask(final String channel, final String message) {
        super(channel, message);
    }

    @Override
    public void run() {
        logger.info(channel+ " start with message " + message);

        try {
            Thread.sleep(3000);
            logger.info(channel+ " work done");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
