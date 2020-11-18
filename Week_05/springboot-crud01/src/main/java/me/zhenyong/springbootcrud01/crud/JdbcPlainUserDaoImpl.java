package me.zhenyong.springbootcrud01.crud;

import lombok.extern.slf4j.Slf4j;
import me.zhenyong.springbootcrud01.config.JdbcCustomUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户CRUD实现类 (纯JDBC API实现)
 *
 * @author zhenyong
 * @version 1.0
 * @date 2020/11/18 4:21 下午
 * @since JDK1.8
 */
// @Primary
@Service
@Qualifier("jdbcPlainUserDaoImpl")
@Slf4j
public class JdbcPlainUserDaoImpl implements UserDao {

    @Resource
    private JdbcCustomUtils jdbcUtils;

    @Override
    public User queryById(int id) throws Exception {

        String sql = "select id,name from user where id = ?";

        Object[] queryObjArray = jdbcUtils.query(sql, new Object[]{id});

        ResultSet rs = (ResultSet) queryObjArray[0];

        List<User> list = new ArrayList<>();
        try {
            while (rs.next()) {
                int uid = rs.getInt(1);
                String uname = rs.getString(2);

                User user = new User(uid, uname);

                list.add(user);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        PreparedStatement pst = (PreparedStatement) queryObjArray[1];
        Connection conn = (Connection) queryObjArray[2];

        jdbcUtils.close(conn, pst, rs);

        return list.get(0);
    }

    @Override
    public List<User> queryByPage(int pageIndex, int pageSize) throws Exception {

        String sql = "select * from user limit ? , ? ";

        return new ArrayList<>();
    }

    @Override
    public void insert(User user) throws Exception {

        String sql = "insert into `user` (`id`,`name`) values (?,?)";

        jdbcUtils.update(sql, new Object[]{user.getId(), user.getName()});
    }

    @Override
    public void update(User user) throws Exception {

        String sql = "update `user` set name=? where id=?";

        jdbcUtils.update(sql, new Object[]{user.getName(), user.getId()});

    }

    @Override
    public void deleteById(int id) throws Exception {

        String sql = "delete from `user` where id=?";

        jdbcUtils.update(sql, new Object[]{id});

    }
}
