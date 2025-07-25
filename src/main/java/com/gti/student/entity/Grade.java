package com.gti.student.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "grade")
public class Grade {

    @Id
    @Column(name = "grade_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gradeId;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "subject_id")
    private String subjectId;

    @Column(name = "first_grade")
    private Double firstGrade;
    @Column(name = "second_grade")
    private Double secondGrade;
    @Column(name = "third_grade")
    private Double thirdGrade;
    @Column(name = "final_exam")
    private Double finalExam;
    @Column(name = "over_all", insertable = false, updatable = false)
    private Double overAll;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "subjectId", insertable = false, updatable = false)
    private Subject subject;


    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Subject getSubject() {
        return subject;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public Double getFirstGrade() {
        return firstGrade;
    }

    public void setFirstGrade(Double firstGrade) {
        this.firstGrade = firstGrade;
    }

    public Double getSecondGrade() {
        return secondGrade;
    }

    public void setSecondGrade(Double secondGrade) {
        this.secondGrade = secondGrade;
    }

    public Double getThirdGrade() {
        return thirdGrade;
    }

    public void setThirdGrade(Double thirdGrade) {
        this.thirdGrade = thirdGrade;
    }

    public Double getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(Double finalExam) {
        this.finalExam = finalExam;
    }

    public Double getOverAll() {
        return overAll;
    }

    public void setOverAll(Double overAll) {
        this.overAll = overAll;
    }



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return gradeId == grade.gradeId && Objects.equals(studentId, grade.studentId) && Objects.equals(subjectId, grade.subjectId)
                && Objects.equals(firstGrade, grade.firstGrade) && Objects.equals(secondGrade, grade.secondGrade)
                && Objects.equals(thirdGrade, grade.thirdGrade) && Objects.equals(finalExam, grade.finalExam)
                && Objects.equals(overAll, grade.overAll);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gradeId, studentId, subjectId, firstGrade, secondGrade, thirdGrade, finalExam, overAll);
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", studentId=" + studentId +
                ", subjectId=" + subjectId +
                ", firstGrade=" + firstGrade +
                ", secondGrade=" + secondGrade +
                ", thirdGrade=" + thirdGrade +
                ", finalExam=" + finalExam +
                ", overAll=" + overAll +
                '}';
    }
}
