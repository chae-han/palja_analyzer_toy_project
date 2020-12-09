package com.chancrawler.chancrawler.tools.parser;

public class PageParserTask extends Thread{

    private String message = "";
    private String channel = "";

    public PageParserTask(String channel, String message) {
        this.message = message;
        this.channel = channel;
    }

    @Override
    public void run() {

    }
}

//public class ParserTask extends Thread{
//
//    private String message = "";
//
//    public ParserTask(String threadName) {
//        this.message = threadName;
//    }
//
//    @Override
//    public void run() {
//        System.out.println(message);
//        try {
//            Thread.sleep(3000);
//            System.out.println("done");
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//}
