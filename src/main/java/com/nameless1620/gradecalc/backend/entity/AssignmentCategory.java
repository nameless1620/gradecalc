package com.nameless1620.gradecalc.backend.entity;

import javax.persistence.Entity;

@Entity
public class AssignmentCategory extends AbstractEntity {
    private String categoryName;
    private double categoryWeight = 0;
    private int numberOfAssignments = 0;
    private double categoryAverage = 0;

    public AssignmentCategory (){

    }

    public AssignmentCategory (String categoryName, double categoryWeight, int numberOfAssignments, double categoryAverage) {
        this.categoryName = categoryName;
        this.categoryWeight = categoryWeight;
        this.numberOfAssignments = numberOfAssignments;
        this.categoryAverage = categoryAverage;
    }

    public String getCategoryName() {return categoryName;}

    public void setCategoryName(String categoryName) {this.categoryName = categoryName;}

    public double getCategoryWeight() {
        return categoryWeight;
    }

    public int getNumberOfAssignments() { return numberOfAssignments;}

    public void setNumberOfAssignments(int numberOfAssignments) {this.categoryWeight = numberOfAssignments;}

    public void setCategoryWeight(double categoryWeight) {
        this.categoryWeight = categoryWeight;
    }

    public double getCategoryAverage() { return categoryAverage;}

    public void setCategoryAverage( double categoryAverage) {this.categoryAverage= categoryAverage;}

    public double getWeightedAverage() {
        return categoryAverage * categoryWeight;
    }
}

