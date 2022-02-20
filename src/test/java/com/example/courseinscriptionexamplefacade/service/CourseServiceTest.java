package com.example.courseinscriptionexamplefacade.service;

import com.example.courseinscriptionexamplefacade.entity.Course;
import com.example.courseinscriptionexamplefacade.entity.Student;
import com.example.courseinscriptionexamplefacade.repository.CourseRepository;
import com.example.courseinscriptionexamplefacade.repository.StudentRepository;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CourseServiceTest {
    private CourseService subject;
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;

    @BeforeMethod
    public void setUp() {
        courseRepository = mock(CourseRepository.class);
        studentRepository = mock(StudentRepository.class);

        subject = new CourseService(courseRepository, studentRepository);
    }

    @Test(dataProvider = "course")
    public void shouldReturnTheCorrectStudent(Course course, String expectedValue, String actualValue){
        Optional<Course> courseOptional = Optional.of(course);
        when(courseRepository.findById((Long) any())).thenReturn(courseOptional);

        Course realCourse = subject.getCourseById(1L);
        assertThat(realCourse).isEqualTo(course);
        assertThat(expectedValue).isEqualTo(actualValue);
        verify(courseRepository, times(1)).findById((Long)any());
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void shouldRaiseNoSuchElementException(){
        when(courseRepository.findById((Long) any())).thenReturn(Optional.empty());
        subject.getCourseById(any());
    }


    @DataProvider
    public Object[][] course(){
        Student student = new Student();
        Course course = new Course(1L, "CourseName", "TeacherName", true,
                Collections.singletonMap("key", "value"), 1, 1,
                Collections.singleton(student), 1L);
        Course course2 = new Course(1L, "CourseName", "TeacherName", true,
                Collections.singletonMap("key", "value"), 1, 1,
                Collections.singleton(student), 1L);
        return new Object[][]{
                {course, "Hola", "Hola"},
                {course2, "Holaa", "Holaa"}
        };
    }
}