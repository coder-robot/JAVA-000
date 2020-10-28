package org.zhenyong.httpclient.other.guava;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

@SuppressWarnings("UnstableApiUsage")
public class MultipleEventsTest {
    @Test
    public void testMultipleEvents() throws Exception {

        EventBus eventBus = new EventBus("test");
        MultipleListenerDemo multiListener = new MultipleListenerDemo();

        eventBus.register(multiListener);

        eventBus.post(new Integer(100));
        eventBus.post(new Integer(200));
        eventBus.post(new Integer(300));
        eventBus.post(new Long(800));
        eventBus.post(new Long(800990));
        eventBus.post(new Long(800882934));

        System.out.println("LastInteger:" + multiListener.getLastInteger());
        System.out.println("LastLong:" + multiListener.getLastLong());
    }
}