package org.zhenyong.httpclient.other.guava;

/**
 * 消息封装类
 */
public class EventDemo {

    private final int message;

    public EventDemo(int message) {
        this.message = message;
        System.out.println("EventDemo Msg:" + message);
    }

    public int getMessage() {
        return message;
    }
}