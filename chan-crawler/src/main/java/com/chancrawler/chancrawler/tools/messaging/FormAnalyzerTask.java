package com.chancrawler.chancrawler.tools.messaging;

import static com.chancrawler.chancrawler.tools.parser.PageParser.parsing;

public class FormAnalyzerTask extends RedisTask{

    public FormAnalyzerTask(final String channel, final String message) {
        super(channel, message);
    }

    @Override
    public void run() {
        System.out.println(channel + "==" + message);

        try {
            Thread.sleep(3000);
            System.out.println(channel + "==" + "done");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
