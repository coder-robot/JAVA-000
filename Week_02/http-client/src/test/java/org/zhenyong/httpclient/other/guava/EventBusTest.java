package org.zhenyong.httpclient.other.guava;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

@SuppressWarnings("UnstableApiUsage")
public class EventBusTest {

    @Test
    public void testReceiveEvent() throws Exception {

        EventBus eventBus = new EventBus("test");

        EventListenerDemo listener = new EventListenerDemo();

        eventBus.register(listener);
        eventBus.post(new EventDemo(200));
        eventBus.post(new EventDemo(300));
        eventBus.post(new EventDemo(400));

        System.out.println("LastMessage:" + listener.getLastMessage());
    }
}