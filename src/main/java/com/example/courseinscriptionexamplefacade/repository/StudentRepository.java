package com.example.courseinscriptionexamplefacade.repository;

import com.example.courseinscriptionexamplefacade.entity.Course;
import com.example.courseinscriptionexamplefacade.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findById(Long Id);
    Optional<Student> findEndedCoursesById(Long Id);
    Optional<List<Student>> findStudentsInActuallyCoursesByActuallyCoursesId(Long Id);
}
