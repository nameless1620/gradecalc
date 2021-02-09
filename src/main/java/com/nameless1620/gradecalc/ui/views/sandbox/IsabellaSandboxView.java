package com.nameless1620.gradecalc.ui.views.sandbox;

import com.nameless1620.gradecalc.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("GradeCalc | Isabella Sandbox")
@Route(value = "isabellasandbox", layout = MainLayout.class)
public class IsabellaSandboxView extends VerticalLayout {

    public IsabellaSandboxView()
    {
        addClassName("isabellasandbox-view");
        Button button = new Button("Isabella!");
        add(button);
    }

}
