package org.zhenyong.httpclient.other.guava;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

@SuppressWarnings("UnstableApiUsage")
public class DeadEventListenersTest {

    @Test
    public void testDeadEventListeners() throws Exception {

        EventBus eventBus = new EventBus("test");

        DeadEventListenerDemo deadEventListener = new DeadEventListenerDemo();

        eventBus.register(deadEventListener);

        eventBus.post(new EventDemo(200));
        eventBus.post(new EventDemo(300));

        System.out.println("deadEvent:" + deadEventListener.isNotDelivered());

    }
}