package me.zhenyong.springbootcrud01.crud;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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
@Service
@Qualifier("jdbcTemplateUserDaoImpl")
public class JdbcTemplateUserDaoImpl implements UserDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public User queryById(int id) throws Exception {
        String sql = "select * from user where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> queryByPage(int pageIndex, int pageSize) throws Exception {

        String sql = "select * from user limit ? , ? ";

        return jdbcTemplate.query(sql,
                new Object[]{(pageIndex - 1) * pageSize, pageSize},
                new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void insert(User user) throws Exception {

        String sql = "insert into `user` (`id`,`name`) values (?,?)";

        jdbcTemplate.update(sql, user.getId(), user.getName());
    }

    @Override
    public void update(User user) throws Exception {

        String sql = "update `user` set name=? where id=?";

        jdbcTemplate.update(sql, user.getName(), user.getId());
    }

    @Override
    public void deleteById(int id) throws Exception {

        String sql = "delete from `user` where id=?";

        jdbcTemplate.update(sql, id);
    }
}
