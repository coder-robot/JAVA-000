package me.zhenyong.springbootcrud02.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * @author zhenyong
 * @version 1.0
 * @date 2020/11/19 12:50 上午
 * @since JDK1.8
 */
@Slf4j
@Component
@EnableConfigurationProperties(JdbcCustomProperties.class)
public class JdbcCustomDataSource implements DataSource {

    private final JdbcCustomProperties jdbcProperties;

    public JdbcCustomDataSource(JdbcCustomProperties jdbcProperties) {
        this.jdbcProperties = jdbcProperties;
        initJdbcDriver();
    }

    private void initJdbcDriver() {
        try {
            Class.forName(jdbcProperties.getDriverClassName());
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
        }
    }


    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                jdbcProperties.getUrl(),
                jdbcProperties.getUsername(),
                jdbcProperties.getPassword()
        );
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return DriverManager.getConnection(
                jdbcProperties.getUrl(),
                username,
                password
        );
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
