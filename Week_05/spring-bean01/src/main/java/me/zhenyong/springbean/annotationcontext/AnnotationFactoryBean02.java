package me.zhenyong.springbean.annotationcontext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author zhenyong
 * @version 1.0
 * @date 2020/11/16 11:02 下午
 * @since JDK1.8
 */
@Component("afb02")
public class AnnotationFactoryBean02 implements InitializingBean {

    @Override
    public String toString() {
        return "afb02------AnnotationFactoryBean02{}";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afb02------AnnotationFactoryBean02------afterPropertiesSet");
    }
}
