package com.nameless1620.gradecalc.ui.views.sandbox;

import com.nameless1620.gradecalc.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jdk.jfr.internal.Logger;

@PageTitle("GradeCalc | Arya Sandbox")
@Route(value = "aryasandbox", layout = MainLayout.class)
public class AryaSandboxView extends VerticalLayout {

    public AryaSandboxView()
    {
        addClassName("aryasandbox-view");

        TextField temperature = new TextField("Temperature");
        Button calculate = new Button("Calculate!");

        calculate.addClickListener(click -> add(new Paragraph(calculate(temperature.getValue()))));
        add(temperature, calculate);
    }

    private String calculate (String temperature) {

        String output = "";

        try {
            float fah = Float.parseFloat(temperature);
            float cel = ((float)5/9) * (fah - 32);
            output = String.valueOf(fah + "F --> " + cel + "C");
        }
        catch (NumberFormatException e) {
            output = "Not a valid number";
        }

        return output;
    }

}
