package com.jaypal.springjdbcdemo;

import com.jaypal.springjdbcdemo.model.Student;
import com.jaypal.springjdbcdemo.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringJdbcDemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringJdbcDemoApplication.class, args);

        Student student = (Student) context.getBean("student");
        student.setName("Devraj");
        student.setMarks(99);
        student.setRollNo(104);
        System.out.println(student);

        StudentService studentService = context.getBean(StudentService.class);
        studentService.addStudent(student);

        List<Student> students = studentService.getStudents();
        students.forEach(System.out::println);
    }

}
