package me.zhenyong.springbootusestarter01;

import me.zhenyong.spring.boot.pojo.Klass;
import me.zhenyong.spring.boot.pojo.School;
import me.zhenyong.spring.boot.pojo.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@SpringBootApplication
public class SpringbootUseStarter01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootUseStarter01Application.class, args);
    }

    @Resource
    private School school;

    @Resource
    private Klass klass;

    @Resource
    private Student student;

    @GetMapping(value = "/week5/school")
    public School school() {
        return school;
    }

    @GetMapping(value = "/week5/klass")
    public Klass klass() {
        return klass;
    }

    @GetMapping(value = "/week5/student")
    public Student student() {
        return student;
    }

}
