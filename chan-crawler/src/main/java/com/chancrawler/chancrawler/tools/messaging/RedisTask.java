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
