package org.zhenyong.httpclient.other.guava;

import com.google.common.eventbus.Subscribe;

@SuppressWarnings("UnstableApiUsage")
public class MultipleListenerDemo {

    public Integer lastInteger;  
    public Long lastLong;  
   
    @Subscribe
    public void listenInteger(Integer event) {  
        lastInteger = event; 
        System.out.println("event Integer:"+lastInteger);
    }  
   
    @Subscribe  
    public void listenLong(Long event) {  
        lastLong = event; 
        System.out.println("event Long:"+lastLong);
    }  
   
    public Integer getLastInteger() {  
        return lastInteger;  
    }  
   
    public Long getLastLong() {  
        return lastLong;  
    }  
}