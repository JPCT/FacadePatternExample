package com.example.courseinscriptionexamplefacade.repository;

import com.example.courseinscriptionexamplefacade.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findById(Long Id);
}
