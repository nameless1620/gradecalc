//package com.nameless1620.gradecalc.assignment;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//
//@Entity
//@Table
//public class Assignment {
//
//    @Id
//    @SequenceGenerator(
//            name = "assignment_sequence",
//            sequenceName = "assignment_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "assignment_sequence"
//    )
//
//    private Long id;
//    private String name;
//    private String type;
//    private int score;
//    private LocalDate date;
//
//    public Assignment() { }
//
//    public Assignment(Long id, String name, String type, int score, LocalDate date) {
//        this.id = id;
//        this.name = name;
//        this.type = type;
//        this.score = score;
//        this.date = date;
//    }
//
//    public Assignment(String name, String type) {
//        this.name = name;
//        this.type = type;
//    }
//
//    public Assignment(String name, String type, LocalDate date, int score) {
//        this.name = name;
//        this.type = type;
//        this.score = score;
//        this.date = date;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public int getScore() {
//        return score;
//    }
//
//    public LocalDate getDate() {
//        return date;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public void setScore(int score) {
//        this.score = score;
//    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }
//
//    @Override
//    public String toString() {
//        return "Assignment {" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", type='" + type + '\'' +
//                ", score=" + score +
//                ", date=" + date +
//                '}';
//    }
//}
