package me.zhenyong.gateway2.filter.pre;

/**
 * 前置过滤器
 *
 * @author zhenyong
 * @version 1.0
 * @date 2020/11/5 1:09 上午
 * @since JDK1.8
 */
public interface PreFilter {

    /**
     * 对入站数据进行处理
     *
     * @param inbound 入站数据
     * @return 处理后的数据
     */
    String process(String inbound);
}
