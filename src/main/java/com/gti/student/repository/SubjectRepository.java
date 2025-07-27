package com.gti.student.repository;

import com.gti.student.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Optional<Subject> findBySubjectId(String subjectId);
    List<Subject> findByTeacher_TeacherId(Integer teacherId);
    List<Subject> findByCourse_CourseId(String courseId);

}
