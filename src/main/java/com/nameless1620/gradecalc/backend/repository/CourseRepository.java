package com.nameless1620.gradecalc.backend.repository;

import com.nameless1620.gradecalc.backend.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long>{
//    @Query("select c from Course "
//            + "where lower(c.userName) like lower(concat('%', :searchTerm, '%'))"
//    )
//    List<Course> search(@Param("searchTerm") String searchTerm);
}
