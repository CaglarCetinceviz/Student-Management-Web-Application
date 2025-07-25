package com.gti.student.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "teacher")
public class Teacher extends Person{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer teacherId;
    private String password;

    // Constructors
    public Teacher(){
        //Default Constructor
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(teacherId, teacher.teacherId) && Objects.equals(password, teacher.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), teacherId, password);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId='" + teacherId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public void displayInfo() {
        System.out.println("Teacher info: " + getFirstName() + " " + getSurName());
    }
}
