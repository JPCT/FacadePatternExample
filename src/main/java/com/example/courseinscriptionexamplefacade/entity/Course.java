package com.example.courseinscriptionexamplefacade.entity;
import javax.persistence.*;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50, nullable = false)
    private String teacherName;

    private Boolean available;

    @ElementCollection
    private Map<String, String> schedule;

    @Column(nullable = false)
    private int maxCapacity;

    @Column(nullable = false)
    private int numberOfCredits;

    @ManyToMany(mappedBy = "actuallyCourses")
    private Set<Student> students;

    private Long preConditionCourseId;

    public Course() {
    }

    public Course(Long id, String name, String teacherName, Boolean available, Map<String, String> schedule, int maxCapacity, int numberOfCredits, Set<Student> students, Long preConditionCourseId) {
        this.id = id;
        this.name = name;
        this.teacherName = teacherName;
        this.available = available;
        this.schedule = schedule;
        this.maxCapacity = maxCapacity;
        this.numberOfCredits = numberOfCredits;
        this.students = students;
        this.preConditionCourseId = preConditionCourseId;
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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Map<String, String> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<String, String> schedule) {
        this.schedule = schedule;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Long getPreConditionCourseId() {
        return preConditionCourseId;
    }

    public void setPreConditionCourseId(Long preConditionCourseId) {
        this.preConditionCourseId = preConditionCourseId;
    }
}
