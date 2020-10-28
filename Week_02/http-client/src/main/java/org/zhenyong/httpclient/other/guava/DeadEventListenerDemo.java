package org.zhenyong.httpclient.other.guava;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

@SuppressWarnings("UnstableApiUsage")
public class DeadEventListenerDemo {

    boolean notDelivered = false;

    @Subscribe
    public void listen(DeadEvent event) {
        notDelivered = true;
        System.out.println("dead event: " + event.getEvent());
        System.out.println("dead source: " + event.getSource());
    }

    public boolean isNotDelivered() {
        return notDelivered;
    }
}