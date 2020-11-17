package me.zhenyong.spring.boot.prop;

import lombok.Getter;
import lombok.Setter;
import me.zhenyong.spring.boot.pojo.Klass;
import me.zhenyong.spring.boot.pojo.School;
import me.zhenyong.spring.boot.pojo.Student;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

/**
 * Spring boot properties configuration.
 */
@ConfigurationProperties(prefix = "spring.week05.zhenyong")
@Getter
@Setter
public final class SpringBootPropertiesConfiguration {

    private Properties props = new Properties();
}