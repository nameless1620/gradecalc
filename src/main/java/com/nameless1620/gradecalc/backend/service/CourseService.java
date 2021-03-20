package com.nameless1620.gradecalc.backend.service;

import com.nameless1620.gradecalc.backend.entity.Course;
import com.nameless1620.gradecalc.backend.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();

    }
}
