package com.nameless1620.gradecalc.backend.entity;

import org.springframework.expression.spel.ast.Assign;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
public class Course extends AbstractEntity {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Assignment> assignments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AssignmentCategory> assignmentCategories = new HashSet<>();
    //TODO https://stackoverflow.com/questions/4334970/hibernate-throws-multiplebagfetchexception-cannot-simultaneously-fetch-multipl
    //TODO https://stackoverflow.com/questions/4334970/hibernate-throws-multiplebagfetchexception-cannot-simultaneously-fetch-multipl/51055523?stw=2#51055523

    private String userName;
    private String courseName;
    private double actualGrade = 0;
    private double desiredGrade = 0;
    private double assignedWeight = 0.0;

    public Course() {

    }

    public Course(String name) {
        this.courseName = name;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void addAssignments(Assignment assignment) {
        this.assignments.add(assignment);
        calculateGrades();
    }

    public void removeAssignment(Assignment assignment) {
        this.assignments.remove(assignment);
        calculateGrades();
    }

    public void addCategories(AssignmentCategory category) {
        this.assignmentCategories.add(category);
        calculateGrades();
    }

    public void removeCategories(AssignmentCategory category) {
        this.assignmentCategories.remove(category);
        calculateGrades();
    }

    public Set<AssignmentCategory> getAssignmentCategories() {
        return assignmentCategories;
    }

    public Set<Assignment> getAssignmentsByCategory(AssignmentCategory category) {
        return assignments.stream().filter(assignment -> assignment.getCategory() == category).collect(Collectors.toSet());
    }

    public void addAssignmentCategory(AssignmentCategory assignmentCategory) {
        this.assignmentCategories.add(assignmentCategory);
        calculateGrades();
    }

    public void calculateGrades() {
        //TODO: Eliminate need to call this explicitly when assignment/category fields are updated
        assignedWeight = 0;

        assignmentCategories.stream().forEach(e -> {
            Set<Assignment> filteredAssignments = getAssignmentsByCategory(e);
            if (filteredAssignments.size() == 0) {
                e.setNumberOfAssignments(0);
                e.setCategoryAverage(0);
            }
            else {
                e.setNumberOfAssignments(filteredAssignments.size());
                double totalAverage = filteredAssignments.stream().map(Assignment::getGrade).reduce(0.0, Double::sum);
                //TODO: Refactor safe divide
                e.setCategoryAverage(totalAverage / filteredAssignments.size());
            }
            assignedWeight += e.getCategoryWeight();
        });

        //Handle unassigned category
        double unassignedAverage = 0;
        double unassignedWeightedAverage = 0;
        Set<Assignment> unassignedAssignments = getAssignmentsByCategory(null);
        if (unassignedAssignments.size() != 0) {
            unassignedAverage = unassignedAssignments.stream().map(Assignment::getGrade).reduce(0.0, Double::sum);
            unassignedWeightedAverage = (unassignedAverage / unassignedAssignments.size()) * (100 - assignedWeight);
        }

        //Combine categories and null category for total grade based on assignments
        actualGrade = (assignmentCategories.stream().map(AssignmentCategory::getWeightedAverage).reduce(0.0, Double::sum)
                + unassignedWeightedAverage) / 100;
    }

    public List<String> getAssignmentCategoryNames() {
        return assignmentCategories.stream()
                .map(x -> x.getCategoryName())
                .collect(Collectors.toList());
    }

    public AssignmentCategory getAssignmentCategoryByName(String categoryName) {
        return assignmentCategories.stream()
                .filter(category -> categoryName.equals(category.getCategoryName()))
                .findFirst()
                .orElse(null);
    }

    public double getDesiredGrade() {
        return desiredGrade;
    }

    public void setDesiredGrade(double desiredGrade) {
        this.desiredGrade = desiredGrade;
    }

    public double getActualGrade() {
        return actualGrade;
    }

    public void setActualGrade(double actualGrade) {
        this.actualGrade = actualGrade;
    }

    public double getAssignedWeight() {
        return assignedWeight;
    }

    public void setAssignedWeight(double assignedWeight) {
        this.assignedWeight = assignedWeight;
    }
}
/*
Includes:
    Tests, classwork, quizzes
        -weightage for each assignment group
    * ability to add assignment groups
    * possibly an ability to bypass entering every single grade, if user knows the averages for each of the assignment categories already?

   Ex:
   Test
    weightage: 60%
    number of tests: 3
        test 1: 86; test 2: 78; test 3: 94 {would entail creating an array with the number of tests as the length}
   Quiz
    weightage: 30%
    number of quizzes: 5
        Q1: 90; Q2; 94; Q3; 87; Q4: 75; Q5: 96

   Classwork:
   weightage 10%
   number of classwork: 2
   C1: 100; C2: 95;
 */