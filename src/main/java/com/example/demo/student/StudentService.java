package com.example.demo.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRespository;

    @Autowired
    public StudentService(StudentRepository studentRespository) {
        this.studentRespository = studentRespository;
    }

    public List<Student> getStudent() {
        return studentRespository.findAll();
    }

    public void addNewStudent(Student student) {
        System.out.println(student);
    }
}
