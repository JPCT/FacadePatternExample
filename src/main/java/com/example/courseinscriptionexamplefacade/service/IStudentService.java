package com.example.courseinscriptionexamplefacade.service;

import com.example.courseinscriptionexamplefacade.entity.Student;

public interface IStudentService {
    Student save(Student newStudent);
    Student getUserById(Long id);
    Boolean validateToHaveCoursed(Long courseId, Long studentId);
    Boolean validateToHaveSeenPreCondition(Long courseId, Long studentId);
    Boolean validateSchedule(Long courseId, Long studentId);
    Boolean isCoursing(Long courseId, Long studentId);
    int getNumOfCreditsCoursing(Long studentId);
    Boolean validateIfAvailableCreditsToInscribe(Long studentId, Long courseId);
    Boolean createActuallyCoursingRegister(Long studentId, Long courseId);
    Boolean validateIfExistsStudent(Long id);
}
