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

import javax.sql.DataSource;
import java.util.List;

/**
 * JdbcTemplate CRUD测试
 *
 * @author zhenyong
 * @version 1.0
 * @date 2020/11/18 5:04 下午
 * @since JDK1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootCrud01Application.class)
public class JdbcTemplateUserDaoTest extends TestCase {

    @Autowired
    @Qualifier("jdbcTemplateUserDaoImpl")
    private UserDao jdbcTemplateUserDao;

    @Autowired
    private DataSource dataSource;

    // 默认是 com.zaxxer.hikari.HikariDataSource
    @Test
    public void test1() throws Exception {
        System.out.println(dataSource.getClass().getName());
    }

    @Test
    public void test2() throws Exception {
        List<User> users = jdbcTemplateUserDao.queryByPage(1, 10);
        users.forEach(System.out::println);
    }

    // crud
    @Test
    public void test3() throws Exception {
        int userId = 1;
        jdbcTemplateUserDao.deleteById(userId);
        jdbcTemplateUserDao.insert(new User(userId, "lzy1"));
        Assert.assertEquals("lzy1", jdbcTemplateUserDao.queryById(userId).getName());
        jdbcTemplateUserDao.update(new User(userId, "lzy2"));
        Assert.assertEquals("lzy2", jdbcTemplateUserDao.queryById(userId).getName());
    }
}