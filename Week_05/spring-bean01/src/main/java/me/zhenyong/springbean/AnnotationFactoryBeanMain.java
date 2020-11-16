package me.zhenyong.springbean;

import me.zhenyong.springbean.annotationcontext.AnnotationFactoryBean01;
import me.zhenyong.springbean.annotationcontext.AnnotationFactoryBean02;
import me.zhenyong.springbean.annotationcontext.AnnotationFactoryBean03;
import me.zhenyong.springbean.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * annotation装配bean
 *
 * @author zhenyong
 * @version 1.0
 * @date 2020/11/16 11:05 下午
 * @since JDK1.8
 */
public class AnnotationFactoryBeanMain {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("me.zhenyong.springbean");
        // Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

        AnnotationFactoryBean01 bean01 = context.getBean(AnnotationFactoryBean01.class);
        AnnotationFactoryBean01 bean02 = (AnnotationFactoryBean01) context.getBean("afb01");
        System.out.println("afb01------" + (bean01.getClass().hashCode() == bean02.getClass().hashCode()));

        AnnotationFactoryBean02 bean03 = (AnnotationFactoryBean02) context.getBean("afb02");
        System.out.println(bean03);


        // ApplicationContext context2 = new AnnotationConfigApplicationContext(AnnotationFactoryBean03.class);
        // Arrays.stream(context2.getBeanDefinitionNames()).forEach(System.out::println);
        User user2 = (User) context.getBean("user2");
        System.out.println(user2);


    }
}
