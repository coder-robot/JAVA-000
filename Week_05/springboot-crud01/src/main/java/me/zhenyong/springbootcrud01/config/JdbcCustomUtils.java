package me.zhenyong.springbootcrud01.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * 自定义JDBC帮助类
 *
 * @author zhenyong
 * @version 1.0
 * @date 2020/11/18 11:24 上午
 * @since JDK1.8
 */
@Component
@EnableConfigurationProperties(JdbcProperties.class)
// @RequiredArgsConstructor
@Slf4j
public class JdbcCustomUtils {

    private final JdbcProperties jdbcProperties;

//    static {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            log.error(e.getMessage());
//        }
//    }

    public JdbcCustomUtils(JdbcProperties jdbcProperties) {
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

    /**
     * 连接数据库
     *
     * @return Connection
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    jdbcProperties.getUrl(),
                    jdbcProperties.getUsername(),
                    jdbcProperties.getPassword()
            );
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return conn;
    }

    /**
     * 查询
     *
     * @param sql  查询的sql语句
     * @param args 查询参数
     * @return Object[]
     */
    public Object[] query(String sql, Object[] args) {

        Connection conn = getConnection();

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {

                // converter field type
                if (args[i] instanceof Integer) {
                    args[i] = Integer.parseInt(args[i].toString());
                }

                pst.setObject(i + 1, args[i]);
            }

            rs = pst.executeQuery();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return new Object[]{rs, pst, conn};
    }

    /**
     * 增删改
     *
     * @param sql  传的sql语句
     * @param args 传递的参数
     * @return boolean
     */
    public boolean update(String sql, Object[] args) {

        Connection conn = getConnection();

        PreparedStatement pst = null;
        boolean b = false;
        try {
            pst = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                pst.setObject(i + 1, args[i]);
            }

            b = pst.executeUpdate() == 1;
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        close(conn, pst, null);

        return b;
    }

    /**
     * 关闭资源
     *
     * @param conn 数据库连接
     * @param pst  PreparedStatement
     * @param rs   ResultSet
     */
    public void close(Connection conn, PreparedStatement pst, ResultSet rs) {

        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (pst != null && !pst.isClosed()) {
                pst.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
