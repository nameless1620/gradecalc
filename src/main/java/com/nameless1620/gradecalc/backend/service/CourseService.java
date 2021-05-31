package com.nameless1620.gradecalc.backend.service;

import com.nameless1620.gradecalc.backend.entity.*;
import com.nameless1620.gradecalc.backend.repository.CourseRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CourseService {
    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public void removeCourse(Course course) {
        courseRepository.delete(course);
    }

    @PostConstruct
    public void populateTestData() {
        if (courseRepository.count() == 0) {
            courseRepository.saveAll(
                    Stream.of("AP Calculus BC", "AP Physics Mech", "AP Computer Science", "AP Spanish Language", "Honors American Lit")
                            .map(name -> {
                                Course course = new Course();
                                course.setCourseName(name);
                                course.addAssignments(new Assignment("Quiz 1", 10, 10));
                                course.addAssignments(new Assignment("Unit 1 Test", 10, 5));
                                course.addAssignments(new Assignment("Unit 2 Test", 10, 0));
                                course.addAssignments(new Assignment("Unit 1: Homework", 100, 8));
                                course.addAssignments(new Assignment("Unit 2: Lab", 15, 7));
                                course.addAssignmentCategory(new AssignmentCategory("Test", 20,5,89));
                                course.addAssignmentCategory(new AssignmentCategory("Quiz", 25,6,89));
                                course.addAssignmentCategory(new AssignmentCategory("Homework/Classwork", 5,5,89));
                                course.addAssignmentCategory(new AssignmentCategory("Labs", 50,5,80));
                                return course;
                            })
                            .collect(Collectors.toList()));
        }
    }

    public List<Course> fetchCourses(int offset, int limit) {
        //TODO integrate offset and limit
        return courseRepository.findAll();
    }

    public int getCourseCount() {
        return Math.toIntExact(courseRepository.count());
    }
}
