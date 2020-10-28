package org.zhenyong.httpclient.other.guava;

import com.google.common.eventbus.Subscribe;

/**
 * 消息接收
 */
@SuppressWarnings("UnstableApiUsage")
public class EventListenerDemo {

    public int lastMessage = 0;

    @Subscribe
    public void listen(EventDemo event) {
        lastMessage = event.getMessage();
        System.out.println("Listen Msg:" + lastMessage);
    }

    public int getLastMessage() {
        return lastMessage;
    }
}