package com.gti.student.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Teacher extends Person{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String teacherId;
    private String password;
    private String subjectId;
    private String courseId;

    // Constructors
    public Teacher(){
        //Default Constructor
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(teacherId, teacher.teacherId) && Objects.equals(password, teacher.password)
                && Objects.equals(subjectId, teacher.subjectId) && Objects.equals(courseId, teacher.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), teacherId, password, subjectId, courseId);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId='" + teacherId + '\'' +
                ", password='" + password + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", courseId='" + courseId + '\'' +
                '}';
    }

    @Override
    public void displayInfo() {
        System.out.println("Teacher info: " + getFirstName() + " " + getSurName());
    }
}
