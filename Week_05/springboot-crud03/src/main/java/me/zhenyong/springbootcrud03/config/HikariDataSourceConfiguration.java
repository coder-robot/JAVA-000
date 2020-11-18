package me.zhenyong.springbootcrud03.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据库配置
 */
@Configuration
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class HikariDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("zhenyong.hikari.datasource")
    public HikariDataSource dataSource() {
        return new HikariDataSource();
    }

}
