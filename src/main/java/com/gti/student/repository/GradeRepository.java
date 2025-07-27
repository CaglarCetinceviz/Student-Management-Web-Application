package com.gti.student.repository;

import com.gti.student.dto.StudentGradeInfoDTO;
import com.gti.student.entity.ClassGroup;
import com.gti.student.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade,Integer> {

    Grade findByStudentIdAndSubjectId(Integer studentId,String subjectId);
    boolean existsByStudentIdAndSubjectId(Integer studentId, String subjectId);
    List<Grade> findByStudentId(Integer studentId);

    @Query(value = "SELECT new com.gti.student.dto.StudentGradeInfoDTO(" +
            "cg.course.courseId, s.classCode, g.subjectId, s.studentId, s.firstName, s.surName, " +
            "g.firstGrade, g.secondGrade, g.thirdGrade, g.finalExam, g.overAll) " +
            "FROM Student s " +
            "JOIN ClassGroup cg ON s.classCode = cg.id " +
            "JOIN Grade g ON s.studentId = g.studentId " +
            "WHERE s.classCode = :classCode AND g.subjectId = :subjectId")
    List<StudentGradeInfoDTO> findStudentGradesByClassAndSubject(@Param("classCode") String classCode,
                                                                 @Param("subjectId") String subjectId);



}
