package com.nameless1620.gradecalc.ui.views.sandbox;

import com.nameless1620.gradecalc.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("GradeCalc | Joshika Sandbox")
@Route(value = "joshikasandbox", layout = MainLayout.class)
public class JoshikaSandboxView extends VerticalLayout {

    public JoshikaSandboxView()
    {
        addClassName("joshikasandbox-view");
        TextField hello = new TextField();
        Button button = new Button("Joshika!");
        button.addClickListener(click -> add(new Paragraph(helloName(hello.getValue()))));
        add(hello, button);

    }

    private String helloName(String name) {
        return "Hello "+name+"!";

        //Description of resource and why to use it
        //Link to resource

        //Description of resource and why to use it
        //Link to resource

        //Description of resource and why to use it
        //Link to resource
    }


}
