package me.zhenyong.gateway2.filter.pre;

import org.springframework.stereotype.Component;

@Component
public class PreFilterImpl2 implements PreFilter {
    @Override
    public String process(String inbound) {
        System.out.println("------>" + this + "处理了");
        return inbound;
    }

    @Override
    public String toString() {
        return "PreFilterImpl2{}";
    }
}
