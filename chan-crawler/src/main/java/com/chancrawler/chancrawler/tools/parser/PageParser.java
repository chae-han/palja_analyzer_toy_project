package com.chancrawler.chancrawler.tools.parser;

import com.chancrawler.chancrawler.tools.messaging.FormAnalyzerTask;

import java.util.logging.Logger;

public class PageParser {
    private static Logger logger = Logger.getLogger(FormAnalyzerTask.class.getName());

    public static boolean parsing(String uri) {

        logger.info("PageParser start with message " + uri );
        int val = Integer.parseInt(uri);
        int tmp = 0;

        if(val%2 == 0) {
            for(int i = 0; i < 10000000;  i++) {
                tmp = i;
            }
            logger.info("PageParser message " + uri + " work done");
            return false;
        } else {
            for(int i = 0; i < 5000000;  i++) {
                tmp = i;
            }
            logger.info("PageParser message " + uri + " work retry");
            return true;
        }

    }
}
