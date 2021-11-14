package com.example.courseinscriptionexamplefacade.facade;

import com.example.courseinscriptionexamplefacade.exceptions.CouldNotInscribeException;
import com.example.courseinscriptionexamplefacade.service.CourseService;
import com.example.courseinscriptionexamplefacade.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class InscriptionServiceFacade implements IInscriptionServiceFacade{
    private final StudentService studentService;
    private final CourseService courseService;

    public InscriptionServiceFacade(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Override
    public Boolean inscribeCourse(Long studentId, Long courseId) throws CouldNotInscribeException {
        if (studentService.validateIfExistsStudent(studentId)) {
            if (courseService.validateIfExistsCourse(courseId)) {
                if (!studentService.validateToHaveCoursed(courseId, studentId)) {
                    if (!studentService.isCoursing(courseId, studentId)) {
                        if (studentService.validateSchedule(courseId, studentId)) {
                            if (studentService.validateIfAvailableCreditsToInscribe(studentId, courseId)) {
                                if (studentService.validateToHaveSeenPreCondition(courseId, studentId)) {
                                    return studentService.createActuallyCoursingRegister(studentId, courseId);
                                } else {
                                    throw new CouldNotInscribeException("No ha cursado el prerequisito");
                                }
                            } else {
                                throw new CouldNotInscribeException("Créditos insuficientes");
                            }
                        } else {
                            throw new CouldNotInscribeException("El horario se cruza");
                        }
                    } else {
                        throw new CouldNotInscribeException("El curso ya se encuentra inscrito");
                    }
                } else {
                    throw new CouldNotInscribeException("Ya vió el curso");
                }
            } else {
                throw new CouldNotInscribeException("El curso no existe");
            }
        } else {
            throw new CouldNotInscribeException("El estudiante no existe");
        }
    }
}
