package org.zhenyong.httpclient.other.guava;

import com.google.common.eventbus.Subscribe;

@SuppressWarnings("UnstableApiUsage")
public class NumberListener {

    private Number lastMessage;

    @Subscribe
    public void listen(Number integer) {
        lastMessage = integer;
        System.out.println("--->NumberListener Message:" + lastMessage);
    }

    public Number getLastMessage() {
        return lastMessage;
    }
}  
