package com.example.demo.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Student> studentOptional = studentRespository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRespository.save(student);
    }
}
