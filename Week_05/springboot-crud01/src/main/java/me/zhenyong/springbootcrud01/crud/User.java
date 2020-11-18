package me.zhenyong.springbootcrud01.crud;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表
 *
 * @author zhenyong
 * @version 1.0
 * @date 2020/11/18 1:45 下午
 * @since JDK1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
}
