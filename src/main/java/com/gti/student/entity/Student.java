package com.gti.student.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "student")
public class Student extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String studentId;
    private String classCode;
    private double firstGrade;
    private double secondGrade;
    private double thirdGrade;
    private double finalExam;
    private double overAll;
    private String subjectId;

    // Constructors
    public Student() {
        //Default Constructor
    }


    /*
    // Constructor for grade table
    public Student (String studentId, String subjectId, double firstGrade, double secondGrade, double thirdGrade, double finalExam) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.firstGrade = firstGrade;
        this.secondGrade = secondGrade;
        this.thirdGrade = thirdGrade;
        this.finalExam = finalExam;
    }

    // Constructor for adding new student
    public Student(String firstName, String surName, String ppsn, String gender, String email, String phoneNumber, String addressLineOne,
                   String addressLineTwo, String city, String eircode, LocalDate dateOfBirth, String classCode)
    {
        super(firstName, surName, ppsn, gender, email, phoneNumber, addressLineOne, addressLineTwo, city, eircode, dateOfBirth);
        this.classCode = classCode;
    }

    // Full Constructor except subjectID
    public Student(String firstName, String surName, String ppsn, String gender, String email, String phoneNumber, String addressLineOne,
                   String addressLineTwo, String city, String eircode, LocalDate dateOfBirth, String studentId, String classCode,
                   double firstGrade, double secondGrade, double thirdGrade, double finalExam, double overAll, String subjectId)
    {
        super(firstName, surName, ppsn, gender, email, phoneNumber, addressLineOne, addressLineTwo, city, eircode, dateOfBirth);
        this.studentId = studentId;
        this.classCode = classCode;
        this.firstGrade = firstGrade;
        this.secondGrade = secondGrade;
        this.thirdGrade = thirdGrade;
        this.finalExam = finalExam;
        this.overAll = overAll;
    }

     */

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public double getFirstGrade() {
        return firstGrade;
    }

    public void setFirstGrade(double firstGrade) {
        this.firstGrade = firstGrade;
    }

    public double getSecondGrade() {
        return secondGrade;
    }

    public void setSecondGrade(double secondGrade) {
        this.secondGrade = secondGrade;
    }

    public double getThirdGrade() {
        return thirdGrade;
    }

    public void setThirdGrade(double thirdGrade) {
        this.thirdGrade = thirdGrade;
    }

    public double getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(double finalExam) {
        this.finalExam = finalExam;
    }

    public double getOverAll() {
        return overAll;
    }

    public void setOverAll(double overAll) {
        this.overAll = overAll;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Double.compare(firstGrade, student.firstGrade) == 0 && Double.compare(secondGrade, student.secondGrade) == 0
                && Double.compare(thirdGrade, student.thirdGrade) == 0 && Double.compare(finalExam, student.finalExam) == 0
                && Double.compare(overAll, student.overAll) == 0 && Objects.equals(studentId, student.studentId) && Objects.equals(classCode, student.classCode)
                && Objects.equals(subjectId, student.subjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), studentId, classCode, firstGrade, secondGrade, thirdGrade, finalExam, overAll, subjectId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", classCode='" + classCode + '\'' +
                ", firstGrade=" + firstGrade +
                ", secondGrade=" + secondGrade +
                ", thirdGrade=" + thirdGrade +
                ", finalExam=" + finalExam +
                ", overAll=" + overAll +
                ", subjectId='" + subjectId + '\'' +
                '}';
    }

    @Override
    public void displayInfo() {
        System.out.println("Student info: " + getFirstName() + " " + getSurName());
    }
}
