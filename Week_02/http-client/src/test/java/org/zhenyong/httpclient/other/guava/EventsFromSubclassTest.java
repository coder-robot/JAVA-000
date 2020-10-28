package org.zhenyong.httpclient.other.guava;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

@SuppressWarnings("UnstableApiUsage")
public class EventsFromSubclassTest {

    @Test
    public void testEventsFromSubclass() throws Exception {

        EventBus eventBus = new EventBus("test");

        IntegerListener integerListener = new IntegerListener();
        NumberListener numberListener = new NumberListener();

        eventBus.register(integerListener);
        eventBus.register(numberListener);

        eventBus.post(new Integer(100));

        System.out.println("integerListener message:" + integerListener.getLastMessage());
        System.out.println("numberListener message:" + numberListener.getLastMessage());

        eventBus.post(new Long(200L));

        System.out.println("integerListener message:" + integerListener.getLastMessage());
        System.out.println("numberListener message:" + numberListener.getLastMessage());
    }
}