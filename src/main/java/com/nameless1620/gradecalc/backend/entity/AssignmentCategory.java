package com.nameless1620.gradecalc.backend.entity;

import javax.persistence.Entity;

@Entity
public class AssignmentCategory extends AbstractEntity {
    private String categoryName;
    private double categoryWeight;

    public AssignmentCategory (){

    }

    public AssignmentCategory (String categoryName, double categoryWeight) {
        this.categoryName = categoryName;
        this.categoryWeight = categoryWeight;
    }

    public String getCategoryName() {return categoryName;}

    public void setCategoryName(String categoryName) {this.categoryName = categoryName;}

    public double getCategoryWeight() {
        return categoryWeight;
    }

    public void setCategoryWeight(double categoryWeight) {
        this.categoryWeight = categoryWeight;
    }
}

