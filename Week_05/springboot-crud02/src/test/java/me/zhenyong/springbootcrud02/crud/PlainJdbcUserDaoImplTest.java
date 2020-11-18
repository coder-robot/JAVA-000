package me.zhenyong.springbootcrud02.crud;

import lombok.extern.slf4j.Slf4j;
import me.zhenyong.springbootcrud02.SpringbootCrud02Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootCrud02Application.class)
@Slf4j
public class PlainJdbcUserDaoImplTest {

    @Autowired
    @Qualifier("plainJdbcUserDaoImpl")
    private UserDao plainJdbcUserDao;

    // crud
    @Test
    public void test1() throws Exception {
        int userId = 4;
        plainJdbcUserDao.deleteById(userId);
        plainJdbcUserDao.insert(new User(userId, "lzy4"));
        Assert.assertEquals("lzy4", plainJdbcUserDao.queryById(userId).getName());
        plainJdbcUserDao.update(new User(userId, "lzy5"));
        Assert.assertEquals("lzy5", plainJdbcUserDao.queryById(userId).getName());
    }

    // Batch Insert
    @Test
    public void test2() throws Exception {

        List<User> users = new ArrayList<>();

        for (int i = 1; i < 3; i++) {
            int userId = i * 10;
            plainJdbcUserDao.deleteById(userId);
            users.add(new User(userId, "lzy" + userId));
        }

        plainJdbcUserDao.batchInsert(users);

        for (int i = 1; i < 3; i++) {
            int userId = i * 10;
            System.out.println(plainJdbcUserDao.queryById(userId));
        }
    }
}