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

import static org.junit.Assert.*;

/**
 * @author zhenyong
 * @version 1.0
 * @date 2020/11/19 1:36 上午
 * @since JDK1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootCrud02Application.class)
@Slf4j
public class ApacheDbUtilsUserDaoTest {

    @Autowired
    @Qualifier("apacheDbUtilsUserDaoImpl")
    private UserDao apacheDbUtilsUserDao;

    // crud
    @Test
    public void test1() throws Exception {
        int userId = 3;
        apacheDbUtilsUserDao.deleteById(userId);
        apacheDbUtilsUserDao.insert(new User(userId, "lzy3"));
        Assert.assertEquals("lzy3", apacheDbUtilsUserDao.queryById(userId).getName());
        apacheDbUtilsUserDao.update(new User(userId, "lzy4"));
        Assert.assertEquals("lzy4", apacheDbUtilsUserDao.queryById(userId).getName());
    }
}