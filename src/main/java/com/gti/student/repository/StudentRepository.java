package com.gti.student.repository;

import com.gti.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByStudentId(Integer studentId);
}
