package com.nameless1620.gradecalc.ui.views.sandbox;

import com.nameless1620.gradecalc.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;


@PageTitle("GradeCalc | Joshika Sandbox")
@Route(value = "joshikasandbox", layout = MainLayout.class)
public class JoshikaSandboxView extends VerticalLayout {

    List<String> grades = new ArrayList<String>();

    public JoshikaSandboxView()
    {
        addClassName("joshikasandbox-view");
        TextField grade = new TextField();
        Button button = new Button("Add Grade");
        button.addClickListener(click -> add(new Paragraph(addGrade(grade.getValue()))));
        add(grade, button);


    }

    private String addGrade(String grade) {

        /*
        1. convert string into number
        2. add number to array
        3. print the list of numbers

         */
       // int grade = Integer.parseInt(name);
        String allGrades = "";
        grades.add(grade);
        for(int gradeIterator = 0; gradeIterator < grades.size(); gradeIterator++){
            allGrades += grades.get(gradeIterator);

        }
        //return "Hello "+name+"!";
        return allGrades;
    }


}
