package com.gti.student.repository;

import com.gti.student.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade,Integer> {
    Grade findByStudentIdAndSubjectId(Integer studentId,String subjectId);
    boolean existsByStudentIdAndSubjectId(Integer studentId, String subjectId);

    List<Grade> findByStudentId(Integer studentId);

}
