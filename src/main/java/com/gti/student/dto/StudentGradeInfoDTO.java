package com.gti.student.dto;

public class StudentGradeInfoDTO {
    private String courseId;
    private String classCode;
    private String subjectId;
    private Integer studentId;
    private String firstName;
    private String surName;
    private Double firstGrade;
    private Double secondGrade;
    private Double thirdGrade;
    private Double finalExam;
    private Double overAll;

    public StudentGradeInfoDTO(String courseId, String classCode, String subjectId,
                               Integer studentId, String firstName, String surName,
                               Double firstGrade, Double secondGrade, Double thirdGrade,
                               Double finalExam, Double overAll) {
        this.courseId = courseId;
        this.classCode = classCode;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.firstName = firstName;
        this.surName = surName;
        this.firstGrade = firstGrade;
        this.secondGrade = secondGrade;
        this.thirdGrade = thirdGrade;
        this.finalExam = finalExam;
        this.overAll = overAll;
    }


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
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
}
