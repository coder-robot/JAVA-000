package me.zhenyong.springbootcrud03.crud;

import me.zhenyong.springbootcrud03.SpringbootCrud03Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 用户数据访问测试
 *
 * @author zhenyong
 * @version 1.0
 * @date 2020/11/19 2:17 上午
 * @since JDK1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootCrud03Application.class)
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    // crud
    @Test
    public void test1() throws Exception {
        int userId = 5;
        userDao.deleteById(userId);
        userDao.insert(new User(userId, "lzy5"));
        Assert.assertEquals("lzy5", userDao.queryById(userId).getName());
        userDao.update(new User(userId, "lzy6"));
        Assert.assertEquals("lzy6", userDao.queryById(userId).getName());
    }
}