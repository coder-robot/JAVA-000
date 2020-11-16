package me.zhenyong.springbean.annotationcontext;

import org.springframework.stereotype.Component;

/**
 * @author zhenyong
 * @version 1.0
 * @date 2020/11/16 11:02 下午
 * @since JDK1.8
 */
@Component("afb01")
public class AnnotationFactoryBean01 {

    @Override
    public String toString() {
        return "afb01------AnnotationFactoryBean01{}";
    }
}
