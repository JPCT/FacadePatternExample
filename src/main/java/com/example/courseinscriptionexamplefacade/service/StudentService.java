package com.example.courseinscriptionexamplefacade.service;

import com.example.courseinscriptionexamplefacade.entity.Course;
import com.example.courseinscriptionexamplefacade.entity.Student;
import com.example.courseinscriptionexamplefacade.repository.CourseRepository;
import com.example.courseinscriptionexamplefacade.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService implements IStudentService{
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Student save(Student newStudent){
        return studentRepository.save(newStudent);
    }

    @Override
    public Student getUserById(Long id){
        try{
            return studentRepository.findById(id).get();
        }catch (NoSuchElementException e){
            return null;
        }
    }

    @Override
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

    @Override
    public Boolean validateToHaveSeenPreCondition(Long courseId, Long studentId){
        try{
            Course course = courseRepository.findById(courseId).get();
            if (course.getPreConditionCourseId() != null) {
                return validateToHaveCoursed(course.getPreConditionCourseId(), studentId);
            }else{
                return true;
            }
        }catch (NoSuchElementException e){
            return null;
        }
    }

    @Override
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
            Student student = getUserById(studentId);
            List<Course> courseList = new ArrayList<>(student.getActuallyCourses());
            Course course = courseList.stream().filter(x -> Objects.equals(x.getId(), courseId)).findAny().orElse(null);
            return course != null;
        }catch (NoSuchElementException e){
            return null;
        }
    }

    @Override
    public int getNumOfCreditsCoursing(Long studentId){
        Student student = getUserById(studentId);
        List<Course> courseList = new ArrayList<>(student.getActuallyCourses());
        int credits = 0;
        for (Course course : courseList){
            credits += course.getNumberOfCredits();
        }
        return credits;
    }

    @Override
    public Boolean validateIfAvailableCreditsToInscribe(Long studentId, Long courseId){
        try{
            Course course = courseRepository.findById(courseId).get();
            Student student = getUserById(studentId);
            if (student == null){
                return null;
            }
            return (getNumOfCreditsCoursing(studentId) + course.getNumberOfCredits()) <= student.getMaxNumberOfCredits();
        }catch (NoSuchElementException e){
            return null;
        }
    }

    @Override
    public Boolean createActuallyCoursingRegister(Long studentId, Long courseId){
        try{
            Student student = getUserById(studentId);
            Set<Course> courseSet = student.getActuallyCourses();
            Course course = courseRepository.findById(courseId).get();
            courseSet.add(course);
            student.setActuallyCourses(courseSet);
            studentRepository.save(student);
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    @Override
    public Boolean validateIfExistsStudent(Long id){
        Student student = getUserById(id);
        return student != null;
    }
}
