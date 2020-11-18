package me.zhenyong.springbootcrud01.crud;

import java.util.List;

/**
 * 用户数据访问接口
 *
 * @author zhenyong
 * @version 1.0
 * @date 2020/11/18 3:31 下午
 * @since JDK1.8
 */
public interface UserDao {

    User queryById(int id) throws Exception;

    List<User> queryByPage(int pageIndex, int pageSize) throws Exception;

    void insert(User user) throws Exception;

    void update(User user) throws Exception;

    void deleteById(int id) throws Exception;
}