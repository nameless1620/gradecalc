package com.nameless1620.gradecalc.assignment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.spel.ast.Assign;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class AssignmentConfig {

    @Bean
    CommandLineRunner commandLineRunner(AssignmentRepository repository) {
        return args -> {
            Assignment test1 = new Assignment(
                    "Test 1",
                    "Test",
                    LocalDate.of(2021, JANUARY, 5),
                    75
            );
            Assignment test2 = new Assignment(
                    "Final Exam",
                    "Test",
                    LocalDate.of(2021, JANUARY, 4),
                    90
            );
            Assignment lab1 = new Assignment(
                    "[Lab] Prime",
                    "Labs"
            );
            Assignment quiz1 = new Assignment(
                    "Exam Prep: Loops and Random",
                    "Quizzes",
                    LocalDate.of(2021, JANUARY, 3),
                    100
            );
            Assignment project1 = new Assignment(
                    "[Project] LuckySevens",
                    "Programming Projects",
                    LocalDate.of(2021, JANUARY, 2),
                    100
            );

            repository.saveAll(
                    List.of(test1, test2, lab1, quiz1, project1)
            );
        };
    }
}
