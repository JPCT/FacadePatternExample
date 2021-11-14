package com.example.courseinscriptionexamplefacade.entity;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String email;

    @ManyToMany
    @JoinTable(
            name = "endedCourse_Student",
            joinColumns = @JoinColumn(name = "studenId"),
            inverseJoinColumns = @JoinColumn(name = "courseId"))
    private Set<Course> endedCourses;

    @ManyToMany
    @JoinTable(
            name = "actuallyCourse_Student",
            joinColumns = @JoinColumn(name = "studenId"),
            inverseJoinColumns = @JoinColumn(name = "courseId"))
    private Set<Course> actuallyCourses;

    public Student() {
    }

    public Student(Long id, String name, String email, Set<Course> endedCourses, Set<Course> actuallyCourses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.endedCourses = endedCourses;
        this.actuallyCourses = actuallyCourses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Course> getEndedCourses() {
        return endedCourses;
    }

    public void setEndedCourses(Set<Course> endedCourses) {
        this.endedCourses = endedCourses;
    }

    public Set<Course> getActuallyCourses() {
        return actuallyCourses;
    }

    public void setActuallyCourses(Set<Course> actuallyCourses) {
        this.actuallyCourses = actuallyCourses;
    }
}
