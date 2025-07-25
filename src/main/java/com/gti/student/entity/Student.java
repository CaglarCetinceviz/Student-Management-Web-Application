package com.gti.student.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "student")
public class Student extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
    private String classCode;


    // Constructors
    public Student() {
        //Default Constructor
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return studentId == student.studentId && Objects.equals(classCode, student.classCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), studentId, classCode);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", classCode='" + classCode + '\'' +
                '}';
    }

    @Override
    public void displayInfo() {
        System.out.println("Student info: " + getFirstName() + " " + getSurName());
    }
}
