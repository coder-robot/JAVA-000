package me.zhenyong.springbean;

import me.zhenyong.springbean.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

/**
 * xml装配bean
 *
 * @author zhenyong
 * @version 1.0
 * @date 2020/11/16 10:47 下午
 * @since JDK1.8
 */
@Resource
public class XmlFactoryBeanMain {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:appContext-spring5.3.1.xml");

        User user = (User) context.getBean("user1");

        System.out.println(user);
    }
}
