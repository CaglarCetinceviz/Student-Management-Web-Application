package com.gti.student.repository;

import com.gti.student.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findByEmail(String email);
}
