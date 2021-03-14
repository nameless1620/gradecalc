package com.nameless1620.gradecalc.ui.views.sandbox;

import com.nameless1620.gradecalc.backend.entity.Assignment;
import com.nameless1620.gradecalc.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@PageTitle("GradeCalc | Joshika Sandbox")
@Route(value = "joshikasandbox", layout = MainLayout.class)
public class JoshikaSandboxView extends VerticalLayout {

    List<Assignment> assignments = new ArrayList<Assignment>();

    Grid<Assignment> assignmentGrid = new Grid<>(Assignment.class);

    public JoshikaSandboxView()
    {
        assignmentGrid.setColumns("name", "questions", "wrongQuestions","grade");
        assignmentGrid.setItems(assignments);
        addClassName("joshikasandbox-view");
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
        add(assignment, questions, wrongQuestions, addAssignment, assignmentGrid);


    }

    //TODO: Add a method to delete an assignment (hint, look at add assignment)
    //TODO: Add a method to update an assignment (hint, look at add assignment)
    //TODO: Add a way to categorize an assignment (hint, you will need to update backend.entity.Assignment.java)

    private void addAssignment(String name, String questions, String wrongQuestions) {
        Assignment assignment = new Assignment(name, Double.parseDouble(questions), Double.parseDouble(wrongQuestions));
        assignments.add(assignment);
    }


}
