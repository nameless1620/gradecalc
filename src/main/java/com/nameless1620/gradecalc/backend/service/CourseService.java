package com.nameless1620.gradecalc.backend.service;

import com.nameless1620.gradecalc.backend.entity.Company;
import com.nameless1620.gradecalc.backend.entity.Contact;
import com.nameless1620.gradecalc.backend.entity.Course;
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

    @PostConstruct
    public void populateTestData() {
        if (courseRepository.count() == 0) {
            courseRepository.saveAll(
                    Stream.of("Calculus", "Physics", "Computer Science")
                            .map(Course::new)
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
}
