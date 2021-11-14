package com.example.courseinscriptionexamplefacade.service;

import com.example.courseinscriptionexamplefacade.entity.Student;
import com.example.courseinscriptionexamplefacade.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student save(Student newStudent){
        return studentRepository.save(newStudent);
    }

    public Student getUserById(Long id){
        try{
            return studentRepository.findById(id).get();
        }catch (NoSuchElementException e){
            return null;
        }
    }
}
