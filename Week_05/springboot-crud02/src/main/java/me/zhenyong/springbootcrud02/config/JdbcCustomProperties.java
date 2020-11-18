package me.zhenyong.springbootcrud02.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 自定义Jdbc数据库连接
 *
 * @author zhenyong
 * @version 1.0
 * @date 2020/11/18 11:10 上午
 * @since JDK1.8
 */
@ConfigurationProperties(prefix = "zhenyong.datasource")
@Getter
@Setter
public class JdbcCustomProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
