package com.nameless1620.gradecalc.backend.repository;

import com.nameless1620.gradecalc.backend.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long>{
}
