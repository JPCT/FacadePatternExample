package com.example.courseinscriptionexamplefacade.service;

import com.example.courseinscriptionexamplefacade.entity.Course;

public interface ICourseService {
    Course save(Course newCourse);
    Course getCourseById(Long id);
    int getStudentsInCourse(Long id);
    Boolean couldInscribe(Long id);
    Boolean validateIfExistsCourse(Long id);
}
