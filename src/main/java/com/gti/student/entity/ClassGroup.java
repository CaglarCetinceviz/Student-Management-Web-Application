package com.gti.student.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "class_group")
public class ClassGroup {

    @Id
    @Column(name = "class_code")
    private String id;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClassGroup that = (ClassGroup) o;
        return Objects.equals(id, that.id) && Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, course);
    }

    @Override
    public String toString() {
        return "ClassGroup{" +
                "id='" + id + '\'' +
                ", course=" + course +
                '}';
    }
}
