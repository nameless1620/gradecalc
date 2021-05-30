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
                    Stream.of("Calculus", "Physics", "Computer Science")
                            .map(name -> {
                                Course course = new Course();
                                course.setCourseName(name);
                                course.addAssignments(new Assignment("Test 1", 10, 10));
                                course.addAssignments(new Assignment("Test 2", 10, 5));
                                course.addAssignments(new Assignment("Test 3", 10, 0));
                                course.addAssignmentCategory(new AssignmentCategory("Test", 20,5,89));
                                course.addAssignmentCategory(new AssignmentCategory("Quiz", 25,6,89));
                                course.addAssignmentCategory(new AssignmentCategory("Homework/Classwork", 5,5,89));
                                course.addAssignmentCategory(new AssignmentCategory("Labs", 50,5,80));
//                                Course::new
                                return course;
                            })
                            .collect(Collectors.toList()));
        }


//        if (courseRepository.count() == 0) {
//            Random r = new Random(0);
//            List<Course> courses = courseRepository.findAll();
//            courseRepository.saveAll(
//                    Stream.of("Gabrielle Patel", "Brian Robinson", "Eduardo Haugen",
//                            "Koen Johansen", "Alejandro Macdonald", "Angel Karlsson", "Yahir Gustavsson", "Haiden Svensson",
//                            "Emily Stewart", "Corinne Davis", "Ryann Davis", "Yurem Jackson", "Kelly Gustavsson",
//                            "Eileen Walker", "Katelyn Martin", "Israel Carlsson", "Quinn Hansson", "Makena Smith",
//                            "Danielle Watson", "Leland Harris", "Gunner Karlsen", "Jamar Olsson", "Lara Martin",
//                            "Ann Andersson", "Remington Andersson", "Rene Carlsson", "Elvis Olsen", "Solomon Olsen",
//                            "Jaydan Jackson", "Bernard Nilsen")
//                            .map(name -> {
//                                String[] split = name.split(" ");
//                                Contact contact = new Contact();
//                                contact.setFirstName(split[0]);
//                                contact.setLastName(split[1]);
//                                contact.setCompany(courses.get(r.nextInt(courses.size())));
//                                contact.setStatus(Contact.Status.values()[r.nextInt(Contact.Status.values().length)]);
//                                String email = (contact.getFirstName() + "." + contact.getLastName() + "@" + contact.getCompany().getName().replaceAll("[\\s-]", "") + ".com").toLowerCase();
//                                contact.setEmail(email);
//                                return contact;
//                            }).collect(Collectors.toList()));
//        }
    }

    public List<Course> fetchCourses(int offset, int limit) {
        //TODO integrate offset and limit
        return courseRepository.findAll();
    }

    public int getCourseCount() {
        return (int)courseRepository.count();
    }
}
