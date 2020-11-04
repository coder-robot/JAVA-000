package me.zhenyong.gateway1.filter.post;

import org.springframework.stereotype.Component;

@Component
public class PostFilterImpl1 implements PostFilter {

    @Override
    public String process(String outbound) {
        System.out.println("------>" + this + "处理了");
        return outbound;
    }

    @Override
    public String toString() {
        return "PostFilterImpl1{}";
    }
}
