package com.gti.student.repository;

import com.gti.student.entity.ClassGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassGroupRepository extends JpaRepository<ClassGroup, String> {
    List<ClassGroup> findByCourse_CourseId(String courseId);
}
