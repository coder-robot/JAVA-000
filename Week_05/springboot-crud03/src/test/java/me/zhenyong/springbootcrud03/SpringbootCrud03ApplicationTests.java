package me.zhenyong.springbootcrud03;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootCrud03Application.class)
public class SpringbootCrud03ApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void contextLoads() {
        System.out.println("------>dataSource: " + dataSource.getClass().getName());
    }

}
