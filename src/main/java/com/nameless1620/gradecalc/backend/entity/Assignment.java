package com.nameless1620.gradecalc.backend.entity;

import javax.persistence.*;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Entity
public class Assignment extends AbstractEntity {
    private String name;

    @ManyToOne
    private AssignmentCategory category;
    private double questions;
    private double wrongQuestions;
    private double grade;
    private boolean doubleWeighted;

    public Assignment(){

    }

    public Assignment(String name, double questions, double wrongQuestions){
        this.name = name;
        this.questions = questions;
        this.wrongQuestions = wrongQuestions;
        calculate();
    }

    public Assignment(String name, AssignmentCategory category, double questions, double wrongQuestions){
        this.name = name;
        this.category = category;
        this.questions = questions;
        this.wrongQuestions = wrongQuestions;
        calculate();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getQuestions() {
        return questions;
    }

    public void setQuestions(double questions) {
        this.questions = questions;
        calculate();
    }

    public double getWrongQuestions() {
        return wrongQuestions;
    }

    public void setWrongQuestions(double wrongQuestions) {
        this.wrongQuestions = wrongQuestions;
        calculate();
    }

    private void calculate () {
        this.grade = (this.questions - this.wrongQuestions) / this.questions;
    }

    public double getGrade() {
        return grade;
    }

    public AssignmentCategory getCategory() {
        return this.category;
    }

    public String getCategoryName() {
        if (category == null) {
            return "None";
        }
        return category.getCategoryName();
    }

    public void setCategory(AssignmentCategory category) {
        if (category != null) //TODO Find what is trying to set this to null :)
            this.category = category;
    }

//    public void setCategory(String category) {
//        //this.category = category;
//        //TODO: Lookup category by name
//
//    }
}
