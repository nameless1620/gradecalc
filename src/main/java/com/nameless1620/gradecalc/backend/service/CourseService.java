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
                                AssignmentCategory test = new AssignmentCategory("Test", 20, 0, 0);
                                AssignmentCategory quiz = new AssignmentCategory("Quiz", 25, 0, 0);
                                AssignmentCategory homework = new AssignmentCategory("Homework/Classworkd", 5, 0, 0);
                                AssignmentCategory labs = new AssignmentCategory("Labs", 50, 0, 0);

                                Course course = new Course();
                                course.setCourseName(name);

                                course.addAssignmentCategory(test);
                                course.addAssignmentCategory(quiz);
                                course.addAssignmentCategory(homework);
                                course.addAssignmentCategory(labs);

                                Random random = new Random();

                                course.addAssignments(new Assignment("Quiz 1", quiz, 10, random.nextInt(10)));
                                course.addAssignments(new Assignment("Unit 1 Test", test, 100, random.nextInt(100)));
                                course.addAssignments(new Assignment("Unit 2 Test", test, 100, random.nextInt(100)));
                                course.addAssignments(new Assignment("Unit 1: Homework", homework, 100, random.nextInt(100)));
                                course.addAssignments(new Assignment("Unit 2: Lab", labs, 15, random.nextInt(10)));
                                course.calculateGrades();
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
