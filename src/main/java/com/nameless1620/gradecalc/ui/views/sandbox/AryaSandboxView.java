package com.nameless1620.gradecalc.ui.views.sandbox;

import com.nameless1620.gradecalc.backend.entity.Assignment;
import com.nameless1620.gradecalc.backend.entity.Company;
import com.nameless1620.gradecalc.backend.entity.Course;
import com.nameless1620.gradecalc.backend.service.CompanyService;
import com.nameless1620.gradecalc.backend.service.CourseService;
import com.nameless1620.gradecalc.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@PageTitle("GradeCalc | Arya Sandbox")
@Route(value = "aryasandbox", layout = MainLayout.class)
public class AryaSandboxView extends VerticalLayout {

    private final CourseService courseService;

    List<Assignment> assignments = new ArrayList<Assignment>();
    Grid<Assignment> assignmentGrid = new Grid<>(Assignment.class);
    List<Course> courses = new ArrayList<Course>();
    Grid<Course> courseGrid = new Grid<>(Course.class);


    public AryaSandboxView(CourseService courseService)
    {
        this.courseService = courseService;
        configureCourseGrid();


        addClassName("aryasandbox-view");

        assignmentGrid.setColumns("name", "questions", "wrongQuestions","grade");
        assignmentGrid.setItems(assignments);

        TextField assignment = new TextField("Assignment","Ex: Test 1");
        TextField questions = new TextField("Number of Questions", "Ex: 35");
        TextField wrongQuestions = new TextField("Number of Wrong Questions","Ex: 4");
        Button addAssignment = new Button("Add Assignment", event -> {
            addAssignment(
                    assignment.getValue(),
                    questions.getValue(),
                    wrongQuestions.getValue());
            assignmentGrid.getDataProvider().refreshAll();
        });
        Button deleteAssignment = new Button("Remove Assignment", buttonClickEvent -> {
            deleteAssignment();
            assignmentGrid.getDataProvider().refreshAll();
        });
        add(assignment, questions, wrongQuestions, addAssignment, assignmentGrid);

        Button updateAssignment = new Button("Edit Assignment", buttonClickEvent -> {
            updateAssignment();
        });
        add(courseGrid, assignment, questions, wrongQuestions, addAssignment, assignmentGrid, deleteAssignment, updateAssignment);

        updateCourseList();


//        TextField testGrade = new TextField("Grade!");
//        Button calculate = new Button("Calculate!");
//
//        calculate.addClickListener(click -> add(new Paragraph(calculate(35,10))));
//        add(testGrade, calculate);
//
//
//        TextField testAverage = new TextField(" Test Sum");
//        TextField testAmount = new TextField("Number of Tests");
//        Button average = new Button("Calculate");
//
//        average.addClickListener(click -> add(new Paragraph(average(289,3 ))));
//        add(testAverage,testAmount, average);
//
//        System.out.println();
    }

    private void configureCourseGrid() {
        courseGrid.addClassName("course-grid");
//        courseGrid.setSizeFull();
        courseGrid.removeColumnByKey("assignments");
        courseGrid.setColumns("courseName", "actualGrade", "desiredGrade");
//        courseGrid.addColumn(contact -> {
//            Company company = contact.getCompany();
//            return company == null ? "-": company.getName();
//        }).setHeader("Company");

        courseGrid.getColumns().forEach(col -> col.setAutoWidth(true));

//        courseGrid.asSingleSelect().addValueChangeListener(evt -> editContact(evt.getValue()));
    }

    private void updateCourseList() {
        courseGrid.setItems(courseService.findAll());
    }

    private void addAssignment(String name, String questions, String wrongQuestions) {
        Assignment assignment = new Assignment(name, Double.parseDouble(questions), Double.parseDouble(wrongQuestions));
        assignments.add(assignment);
    }
    private void deleteAssignment(){

        Set<Assignment> edit = assignmentGrid.getSelectedItems();
        for(Iterator<Assignment> assignmentIterator = edit.iterator(); assignmentIterator.hasNext(); ){
            Assignment selected = assignmentIterator.next();
            assignments.remove(selected);
        }
    }

    private void updateAssignment(){

    }

    private String calculate (double questions, double wrongQuestions) {

        String output = "";
        double grade = (questions - wrongQuestions) / questions;

//        try {
//            float fah = Float.parseFloat(temperature);
//            float cel = ((float)5/9) * (fah - 32);
//            output = String.valueOf(fah + "F --> " + cel + "C");
//        }
//        catch (NumberFormatException e) {
//            output = "Not a valid number";
//        }
        output += ("Your new grade is " + Double.toString(grade) + ".");

        return output;
    }


    private String average(double testSum, double testAmount)
    {
        String output = "";

        double averageScore = testSum / testAmount;

        output += ("Your test average is " + averageScore);

        return output;

    }

    private String weightage(double testSum){
        String output = "";


        return output;
    }
/*
    -taking averages
    - taking grade percents
    - taking weightage points
 */
}
