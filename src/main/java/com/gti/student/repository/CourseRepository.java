package com.gti.student.repository;

import com.gti.student.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {
    Course findByCourseId(String courseId);
    List<Course> findCourseByCourseId(String courseId);
}
