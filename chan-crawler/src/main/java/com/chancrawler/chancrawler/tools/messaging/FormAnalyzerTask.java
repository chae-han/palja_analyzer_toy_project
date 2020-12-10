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
        logger.info("[FormAnalyzerTask start]========== " + channel + ", " + message);

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
