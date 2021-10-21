package com.example.demo.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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

    public void deleteStudent(Long studentId) {

        boolean exists = studentRespository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exists");
        }
        studentRespository.deleteById(studentId);
    }


    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRespository.findById(studentId).orElseThrow( () -> new IllegalStateException("Student with id " + studentId + "does not exists"));
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRespository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
