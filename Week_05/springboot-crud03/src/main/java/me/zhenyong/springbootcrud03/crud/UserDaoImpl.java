package me.zhenyong.springbootcrud03.crud;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户数据访问接口实现
 *
 * @author zhenyong
 * @version 1.0
 * @date 2020/11/19 12:28 上午
 * @since JDK1.8
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Resource
    private HikariDataSource dataSource;

    @Override
    public User queryById(int id) throws Exception {

        String sql = "select id,name from user where id = ?";

        QueryRunner queryRunner = new QueryRunner(dataSource);
        return queryRunner.query(sql, new BeanHandler<>(User.class), id);
    }

    @Override
    public void insert(User user) throws Exception {

        String sql = "insert into `user` (`id`,`name`) values (?,?)";

        QueryRunner queryRunner = new QueryRunner(dataSource);
        queryRunner.update(sql, user.getId(), user.getName());
    }

    @Override
    public void update(User user) throws Exception {

        String sql = "update `user` set name=? where id=?";

        QueryRunner queryRunner = new QueryRunner(dataSource);
        queryRunner.update(sql, user.getName(), user.getId());
    }

    @Override
    public void deleteById(int id) throws Exception {

        String sql = "delete from `user` where id=?";

        QueryRunner queryRunner = new QueryRunner(dataSource);
        queryRunner.update(sql, id);
    }
}
