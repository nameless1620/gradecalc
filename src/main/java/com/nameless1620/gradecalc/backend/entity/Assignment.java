package com.nameless1620.gradecalc.backend.entity;

import javax.persistence.Entity;

@Entity
public class Assignment extends AbstractEntity {
    private String name;
    private String category;
    private double questions;
    private double wrongQuestions;
    private double grade;
    private boolean doubleWeighted;

    public Assignment(){

    }

    public Assignment(String name, double questions, double wrongQuestions){
        this.name = name;
        this.category = category;
        this.questions = questions;
        this.wrongQuestions = wrongQuestions;
        this.grade = calculate(questions, wrongQuestions);
    }

    public Assignment(String name, String category, double questions, double wrongQuestions){
        this.name = name;
        this.category = category;
        this.questions = questions;
        this.wrongQuestions = wrongQuestions;
        this.grade = calculate(questions, wrongQuestions);
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
    }

    public double getWrongQuestions() {
        return wrongQuestions;
    }

    public void setWrongQuestions(double wrongQuestions) {
        this.wrongQuestions = wrongQuestions;
    }
    private double calculate (double questions, double wrongQuestions) {
        return (questions - wrongQuestions) / questions;
    }

    public double getGrade() {
        return grade;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
