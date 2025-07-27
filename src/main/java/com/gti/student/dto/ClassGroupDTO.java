package com.gti.student.dto;

public class ClassGroupDTO {
    private String id;
    private String courseId;

    public ClassGroupDTO(String id, String courseId) {
        this.id = id;
        this.courseId = courseId;
    }

    public String getId() { return id; }
    public String getCourseId() { return courseId; }
}

