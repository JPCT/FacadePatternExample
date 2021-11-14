package com.example.courseinscriptionexamplefacade.service;

import com.example.courseinscriptionexamplefacade.entity.Course;
import com.example.courseinscriptionexamplefacade.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CourseService {
    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
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
}
