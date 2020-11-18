package me.zhenyong.springbootcrud02.crud;

import me.zhenyong.springbootcrud02.config.JdbcCustomDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户CRUD实现类
 *
 * @author zhenyong
 * @version 1.0
 * @date 2020/11/18 4:21 下午
 * @since JDK1.8
 */
@Repository
@Qualifier("apacheDbUtilsUserDaoImpl")
public class ApacheDbUtilsUserDaoImpl implements UserDao {

    @Resource
    private JdbcCustomDataSource dataSource;

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
    public void batchInsert(List<User> users) throws Exception {
        // todo
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
