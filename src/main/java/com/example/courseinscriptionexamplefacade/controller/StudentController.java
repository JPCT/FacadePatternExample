package com.example.courseinscriptionexamplefacade.controller;

import com.example.courseinscriptionexamplefacade.entity.Student;
import com.example.courseinscriptionexamplefacade.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/get")
    ResponseEntity<Student> getStudent(@RequestParam(value = "id") Long id){
        Student student = studentService.getUserById(id);
        if (student == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/create")
    ResponseEntity<Student> newStudent(@RequestBody Student newStudent){
        return new ResponseEntity<>(studentService.save(newStudent), HttpStatus.CREATED);
    }
}
