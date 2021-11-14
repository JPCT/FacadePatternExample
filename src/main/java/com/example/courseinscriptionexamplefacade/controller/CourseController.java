package com.example.courseinscriptionexamplefacade.controller;

import com.example.courseinscriptionexamplefacade.entity.Course;
import com.example.courseinscriptionexamplefacade.entity.Student;
import com.example.courseinscriptionexamplefacade.service.CourseService;
import com.example.courseinscriptionexamplefacade.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/get")
    ResponseEntity<Course> getCourse(@RequestParam(value = "id") Long id){
        Course course = courseService.getCourseById(id);
        if (course == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping("/create")
    ResponseEntity<Course> newCourse(@RequestBody Course newCourse){
        return new ResponseEntity<>(courseService.save(newCourse), HttpStatus.CREATED);
    }

    @GetMapping("/getstudents")
    ResponseEntity<String> getStudents(@RequestParam(value = "id") Long id){
        int studentsInCourse = courseService.getStudentsInCourse(id);
        if (studentsInCourse == -1){
            return new ResponseEntity<>(String.valueOf(studentsInCourse), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(String.valueOf(studentsInCourse), HttpStatus.OK);
    }
}
