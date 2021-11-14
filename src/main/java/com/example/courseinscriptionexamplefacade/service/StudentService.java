package com.example.courseinscriptionexamplefacade.service;

import com.example.courseinscriptionexamplefacade.entity.Course;
import com.example.courseinscriptionexamplefacade.entity.Student;
import com.example.courseinscriptionexamplefacade.repository.CourseRepository;
import com.example.courseinscriptionexamplefacade.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
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

    public Boolean validateToHaveCoursed(Long courseId, Long studentId){
        try {
            Student student = studentRepository.findEndedCoursesById(courseId).get();
            if (student != null && student.getId() == studentId){
                return true;
            }
            return false;
        }catch (NoSuchElementException e){
            return null;
        }
    }

    public Boolean validateToHaveSeenPreCondition(Long courseId, Long studentId){
        try{
            Course course = courseRepository.findById(courseId).get();
            return validateToHaveCoursed(course.getPreConditionCourseId(), studentId);
        }catch (NoSuchElementException e){
            return null;
        }
    }

    public Boolean validateSchedule(Long courseId, Long studentId){
        try{
            Course course = courseRepository.findById(courseId).get();
            Student student = studentRepository.findById(studentId).get();
            List<Course> courseList = new ArrayList<>(student.getActuallyCourses());
            return checkScheduleMatch(course, courseList);
        }catch (NoSuchElementException e){
            return null;
        }
    }

    private Boolean checkScheduleMatch(Course courseToMatch, List<Course> courseList){
        for (String dayCourseToMatch : courseToMatch.getSchedule().keySet()){
            for (Course course : courseList){
                if (course.getSchedule().get(dayCourseToMatch).equals(courseToMatch.getSchedule().get(dayCourseToMatch))){
                    return false;
                }
            }
        }
        return true;
    }

    public Boolean isCoursing(Long courseId, Long studentId){
        try{
            Student student = studentRepository.findById(studentId).get();
            List<Course> courseList = new ArrayList<>(student.getActuallyCourses());
            Course course = courseList.stream().filter(x -> Objects.equals(x.getId(), courseId)).findAny().orElse(null);
            return course != null;
        }catch (NoSuchElementException e){
            return null;
        }
    }
}
