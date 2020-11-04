package me.zhenyong.gateway1.filter.post;

/**
 * 后置过滤器
 *
 * @author zhenyong
 * @version 1.0
 * @date 2020/11/5 1:09 上午
 * @since JDK1.8
 */
public interface PostFilter {

    /**
     * 对出站数据进行处理
     *
     * @param outbound 出站数据
     * @return 处理后的数据
     */
    String process(String outbound);
}
