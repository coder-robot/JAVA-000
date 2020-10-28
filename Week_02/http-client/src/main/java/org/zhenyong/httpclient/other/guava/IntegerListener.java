package org.zhenyong.httpclient.other.guava;

import com.google.common.eventbus.Subscribe;

@SuppressWarnings("UnstableApiUsage")
public class IntegerListener {

    private Integer lastMessage;

    @Subscribe
    public void listen(Integer integer) {
        lastMessage = integer;
        System.out.println("--->IntegerListener Message:" + lastMessage);
    }

    public Integer getLastMessage() {
        return lastMessage;
    }
}