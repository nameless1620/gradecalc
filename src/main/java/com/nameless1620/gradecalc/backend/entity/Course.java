package com.nameless1620.gradecalc.backend.entity;

import javax.persistence.*;
import java.util.*;

@Entity
public class Course extends AbstractEntity {

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Assignment> assignments = new ArrayList<Assignment>();
  //  private List<AssignmentCategory> assignments = new ArrayList<Assignment>();
//    private Map <String, Double> assignmentCategories;
//    private double weightagePerAssignmentCategory;

    private String courseName;
    private double actualGrade;
    private double desiredGrade;

    public Course (){

    }

    public Course (String name) {
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
        calculateGrade();
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

    private void calculateGrade() {
        this.actualGrade = 96;
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