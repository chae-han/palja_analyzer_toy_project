package com.chancrawler.chancrawler.tools.parser;

public class PageParser {
    public static boolean parsing(String uri) {

        int val = Integer.parseInt(uri);
        int tmp = 0;

        if(val%2 == 0) {
            for(int i = 0; i < 10000;  i++) {
                tmp = i;
            }
            System.out.println("done");
            return false;
        } else {
            for(int i = 0; i < 5000;  i++) {
                tmp = i;
            }
            System.out.println("retry");
            return true;
        }

    }
}
