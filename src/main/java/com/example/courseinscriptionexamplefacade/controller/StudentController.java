package com.example.courseinscriptionexamplefacade.controller;

import com.example.courseinscriptionexamplefacade.entity.Student;
import com.example.courseinscriptionexamplefacade.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private StudentService studentService;
    private static final Logger logger = LogManager.getLogger(StudentController.class);

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

    @PostMapping("/registerEndedCourse")
    ResponseEntity<Student> newStudent(@RequestBody Map<String, String> json){
        String studentId = json.get("studentID");
        String courseId = json.get("studentId");
        logger.error(studentId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/coursed")
    ResponseEntity<Boolean> notHaveCoursed(@RequestBody Map<String, String> json){
        Long studentId = Long.parseLong(json.get("studentId"));
        Long courseId = Long.parseLong(json.get("courseId"));
        Boolean coursed = studentService.validateToHaveSeenPreCondition(courseId, studentId);
        if (coursed == null){
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        if (coursed){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getschedule")
    ResponseEntity<Boolean> getStudentSchedule(@RequestBody Map<String, String> json){
        Long studentId = Long.parseLong(json.get("studentId"));
        Long courseId = Long.parseLong(json.get("courseId"));
        Boolean validate = studentService.validateSchedule(courseId,studentId);
        return new ResponseEntity<>(validate, HttpStatus.OK);
    }

    @GetMapping("/iscoursing")
    ResponseEntity<Boolean> isCoursing(@RequestBody Map<String, String> json){
        Long studentId = Long.parseLong(json.get("studentId"));
        Long courseId = Long.parseLong(json.get("courseId"));
        Boolean validate = studentService.isCoursing(courseId,studentId);
        return new ResponseEntity<>(validate, HttpStatus.OK);
    }

    @GetMapping("/couldinscribe")
    ResponseEntity<Boolean> couldInscribe(@RequestBody Map<String, String> json){
        Long studentId = Long.parseLong(json.get("studentId"));
        Long courseId = Long.parseLong(json.get("courseId"));
        Boolean validate = studentService.validateIfAvailableCreditsToInscribe(courseId,studentId);
        if (validate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(validate, HttpStatus.OK);
    }
}
