package com.nameless1620.gradecalc.ui.views.sandbox;

import com.nameless1620.gradecalc.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("GradeCalc | Arya Sandbox")
@Route(value = "aryasandbox", layout = MainLayout.class)
public class AryaSandboxView extends VerticalLayout {


    public AryaSandboxView()
    {
        addClassName("aryasandbox-view");

        TextField testGrade = new TextField("Grade!");
        Button calculate = new Button("Calculate!");

        calculate.addClickListener(click -> add(new Paragraph(calculate(35,10))));
        add(testGrade, calculate);


        TextField testAverage = new TextField(" Test Sum");
        TextField testAmount = new TextField("Number of Tests");
        Button average = new Button("Calculate");

        average.addClickListener(click -> add(new Paragraph(average(289,3 ))));
        add(testAverage,testAmount, average);

        System.out.println();
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
