package me.zhenyong.springbootcrud01.controller;

import lombok.extern.slf4j.Slf4j;
import me.zhenyong.springbootcrud01.crud.User;
import me.zhenyong.springbootcrud01.crud.UserDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * User Restful API
 *
 * @author zhenyong
 * @version 1.0
 * @date 2020/11/18 3:33 下午
 * @since JDK1.8
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    @Qualifier("jdbcTemplateUserDaoImpl")
    private UserDao userDao;

    @GetMapping("/get/{id}")
    public User get(@PathVariable Integer id) {
        try {
            return userDao.queryById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new User(-1, "未知用户");
    }

    @GetMapping("/del/{id}")
    public void del(@PathVariable Integer id) {
        try {
            userDao.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PostMapping("/add")
    public void add(@ModelAttribute User user) {
        try {
            userDao.insert(user);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PostMapping("/update")
    public void update(@ModelAttribute User user) {
        try {
            userDao.update(user);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
