package me.zhenyong.springbean.annotationcontext;

import me.zhenyong.springbean.pojo.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhenyong
 * @version 1.0
 * @date 2020/11/16 11:02 下午
 * @since JDK1.8
 */
@Configuration
// @Component
public class AnnotationFactoryBean03 {

    @Bean(name = "user2")
    @Qualifier(value = "user2")
    public User user() {
        return new User(102, "expert-link");
    }

    @Override
    public String toString() {
        return "AnnotationFactoryBean03{}";
    }
}
