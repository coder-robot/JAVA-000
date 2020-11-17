package me.zhenyong.spring.boot;

import lombok.RequiredArgsConstructor;
import me.zhenyong.spring.boot.pojo.Klass;
import me.zhenyong.spring.boot.pojo.School;
import me.zhenyong.spring.boot.pojo.Student;
import me.zhenyong.spring.boot.prop.SpringBootPropertiesConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Spring boot starter configuration.
 */
@Configuration
@EnableConfigurationProperties(SpringBootPropertiesConfiguration.class)
@ConditionalOnProperty(prefix = "spring.week05", name = "enabled", havingValue = "true", matchIfMissing = true)
@RequiredArgsConstructor
public class SpringBootConfiguration {

    private final SpringBootPropertiesConfiguration props;

    @Bean
    public Student configStudent() {
        Properties props = this.props.getProps();
        if (props == null || props.getProperty("user-id") == null) {
            return new Student().create();
        }
        int userId = Integer.parseInt(props.getProperty("user-id"));
        String userName = props.getProperty("user-name");
        return new Student(userId, userName);
    }

    @Bean
    public Klass configKlass() {
        List<Student> students = new ArrayList<>();
        students.add(configStudent());
        Klass klass = new Klass();
        klass.setStudents(students);
        klass.dong();
        return klass;
    }

    @Bean
    public School configSchool() {
        School school = new School();
        school.setClass1(configKlass());
        school.setStudent100(configStudent());
        return school;
    }
}
