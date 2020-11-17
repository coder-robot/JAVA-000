package me.zhenyong.spring.boot;

import me.zhenyong.spring.boot.prop.SpringBootPropertiesConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootStarterTest.class)
@SpringBootApplication
@ActiveProfiles("common")
public class SpringBootStarterTest {

    @Resource
    private SpringBootPropertiesConfiguration propertiesConfiguration;

    @Value("${spring.week05.enabled}")
    private Boolean week05Enabled;

    @Test
    public void assertEnabled() {
        System.out.println(week05Enabled);
    }

    @Test
    public void assertProps() {
        System.out.println(propertiesConfiguration.getProps());
    }
}