package me.zhenyong.springbootcrud02.crud;

import lombok.extern.slf4j.Slf4j;
import me.zhenyong.springbootcrud02.SpringbootCrud02Application;
import me.zhenyong.springbootcrud02.config.JdbcCustomDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * ApacheDbUtilsTest
 *
 * @author zhenyong
 * @version 1.0
 * @date 2020/11/19 1:10 上午
 * @since JDK1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootCrud02Application.class)
@Slf4j
public class ApacheDbUtilsTest {

    @Autowired
    private JdbcCustomDataSource dataSource;

    @Test
    public void test1() throws SQLException {
        query01();
        query02();
        query03();
        query04();
        query05();
        query06();
        query07();
        query08();
    }

    // ArrayHandler处理类的使用
    private void query01() {
        // 1.创建QueryRunner对象
        QueryRunner qr = new QueryRunner(dataSource);
        // 2.执行查询
        String sql = "select * from user where id = ?";
        Object[] objs = new Object[0];
        try {
            objs = qr.query(sql, new ArrayHandler(), 1);
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
        for (Object o : objs) {
            System.out.println("------>query01: " + o);
        }
    }

    // ArrayListHandler
    private void query02() throws SQLException {
        // 1.创建QueryRunner对象
        QueryRunner qr = new QueryRunner(dataSource);
        String sql = "select * from user limit 10";
        // 2.执行查询
        List<Object[]> objs = qr.query(sql, new ArrayListHandler());
        for (Object[] objects : objs) {
            System.out.println("------>query02: " + objects[0] + "\t" + objects[1]);
        }
    }

    // BeanHandler处理类的使用
    private void query03() throws SQLException {
        // 1.创建QueryRunner对象
        QueryRunner qr = new QueryRunner(dataSource);
        // 2.执行查询
        String sql = "select * from user limit 10";
        User user = qr.query(sql, new BeanHandler<>(User.class));
        System.out.println("------>query03: " + user);
    }

    // BeanListHandler
    private void query04() throws SQLException {
        // 1.创建QueryRunner对象
        QueryRunner qr = new QueryRunner(dataSource);
        // 2.执行查询
        String sql = "select * from user limit 10";
        List<User> users = qr.query(sql, new BeanListHandler<>(User.class));
        for (User user : users) {
            System.out.println("------>query04: " + user);
        }
    }

    // ColumnListHandler处理类的使用
    private void query05() throws SQLException {
        // 1.创建QueryRunner对象
        QueryRunner qr = new QueryRunner(dataSource);
        // 2.执行查询
        String sql = "select * from user limit 10";
        List<Object> objectList = qr.query(sql, new ColumnListHandler<Object>("name"));
        System.out.println("------>query05: " + objectList);
    }

    // MapHandler处理类的使用
    private void query06() throws SQLException {
        // 1.创建QueryRunner对象
        QueryRunner qr = new QueryRunner(dataSource);
        // 2.执行查询
        String sql = "select * from user limit 10";
        Map<String, Object> map = qr.query(sql, new MapHandler());
        // 3.
        System.out.println("------>query06: " + map);
    }

    // MapListHandler处理类的使用
    private void query07() throws SQLException {
        // 1.创建QueryRunner对象
        QueryRunner qr = new QueryRunner(dataSource);
        // 2.执行查询
        String sql = "select * from user limit 10";
        List<Map<String, Object>> maps = qr.query(sql, new MapListHandler());
        // 3.List
        System.out.println("------>query07: " + maps);
    }

    // MapListHandler处理类的使用
    private void query08() throws SQLException {
        // 1.创建QueryRunner对象
        QueryRunner qr = new QueryRunner(dataSource);
        // 2.执行查询
        String sql = "select count(*) from user";
        Long count = qr.query(sql, new ScalarHandler<Long>());
        // 3.List
        System.out.println("------>query08: " + count);
    }
}
