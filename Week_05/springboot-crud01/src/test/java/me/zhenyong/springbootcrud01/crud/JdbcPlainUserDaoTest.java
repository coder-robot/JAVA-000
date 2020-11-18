package me.zhenyong.springbootcrud01.crud;

import junit.framework.TestCase;
import me.zhenyong.springbootcrud01.SpringbootCrud01Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 纯JDBC API操作
 *
 * @author zhenyong
 * @version 1.0
 * @date 2020/11/18 5:04 下午
 * @since JDK1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootCrud01Application.class)
public class JdbcPlainUserDaoTest extends TestCase {

    @Autowired
    @Qualifier("jdbcPlainUserDaoImpl")
    private UserDao jdbcPlainUserDao;

    // crud
    @Test
    public void test1() throws Exception {
        int userId = 2;
        jdbcPlainUserDao.deleteById(userId);
        jdbcPlainUserDao.insert(new User(userId, "lzy2"));
        Assert.assertEquals("lzy2", jdbcPlainUserDao.queryById(userId).getName());
        jdbcPlainUserDao.update(new User(userId, "lzy3"));
        Assert.assertEquals("lzy3", jdbcPlainUserDao.queryById(userId).getName());
    }
}