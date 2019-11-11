package com.itbook;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.List;

public class DatasourceTest {

    private ApplicationContext applicationContext;
    private StudentReposity studentReposity = null;
    private StudentService studentService;

    @Before
    public void setup() {
        System.out.println("开始-------------------------");
        applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        studentReposity = applicationContext.getBean(StudentReposity.class);
        studentService = applicationContext.getBean(StudentService.class);
    }

    @After
    public void destory() {
        applicationContext = null;
        System.out.println("结束-------------------------------");
    }

    @Test
    public void testDatasource() {
        DataSource dataSource = (DataSource) applicationContext.getBean("datasource");
        Assert.assertNotNull(dataSource);
    }

    @Test
    public void testEntityManagerFactory() {
    }

    @Test
    public void testStudentReposity() {
        Student student = studentReposity.findByName("test1");
        System.out.println(student.getId());
        System.out.println(student.getName());
        System.out.println(student.getAge());
    }

    @Test
    public void test1() {
        List<Student> students = studentReposity.getStudents(25);
        for (Student student : students) {
            System.out.println(student.getId() + "    name:    " + student.getName() + "age:   " + student.getAge());
        }
    }

    @Test
    public void test2() {
        studentService.update();
    }
}
