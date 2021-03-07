package com.nameless1620.gradecalc.backend.entity;

import javax.persistence.Entity;

@Entity
public class Assignment extends AbstractEntity {
    private String name;
    private double questions;
    private double wrongQuestions;

    public Assignment(){

    }
    public Assignment(String name, double questions, double wrongQuestions){
        this.name = name;
        this.questions = questions;
        this.wrongQuestions = wrongQuestions;
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
}
