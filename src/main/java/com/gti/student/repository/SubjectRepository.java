package com.gti.student.repository;

import com.gti.student.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Optional<Subject> findBySubjectId(String subjectId);
    List<Subject> findByTeacher_TeacherId(Integer teacherId);

}
