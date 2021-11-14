package com.example.courseinscriptionexamplefacade.service;

import com.example.courseinscriptionexamplefacade.entity.Course;
import com.example.courseinscriptionexamplefacade.entity.Student;
import com.example.courseinscriptionexamplefacade.repository.CourseRepository;
import com.example.courseinscriptionexamplefacade.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseService {
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;

    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public Course save(Course newCourse){
        return courseRepository.save(newCourse);
    }

    public Course getCourseById(Long id){
        try{
            return courseRepository.findById(id).get();
        }catch (NoSuchElementException e){
            return null;
        }
    }

    public int getStudentsInCourse(Long id){
        try{
            courseRepository.findById(id).get();
            List<Student> studentList = studentRepository.findStudentsInActuallyCoursesByActuallyCoursesId(id).get();
            return studentList.size();
        }catch (NoSuchElementException e){
            return -1;
        }
    }

    public Boolean couldInscribe(Long id){
        Course course = getCourseById(id);
        return getStudentsInCourse(id) + 1 <= course.getMaxCapacity();
    }
}
