package com.nameless1620.gradecalc.ui.views.sandbox;

import com.nameless1620.gradecalc.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("GradeCalc | Bhoomi Sandbox")
@Route(value = "bhoomisandbox", layout = MainLayout.class)
public class BhoomiSandboxView extends VerticalLayout {

    public BhoomiSandboxView()
    {
        addClassName("bhoomisandbox-view");
        Button button = new Button("Bhoomi!");
        add(button);
    }

}
