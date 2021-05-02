package com.nameless1620.gradecalc.ui.views.resources;

import com.nameless1620.gradecalc.ui.MainLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import sun.awt.image.ImageWatched;

@PageTitle("GradeCalc | Resources")
@Route(value = "resources", layout = MainLayout.class)
public class ResourcesView extends VerticalLayout {

    public ResourcesView(){

//      TextArea textArea = new TextArea("Do you need help finding extra resources? Here are some resources you can use:");
//      textArea.setPlaceholder("Write here ...");

        //TextField textField = new TextField("Do you need help finding extra resources? Here are some resources you can use:");
        Paragraph paragraph = new Paragraph("Do you need help finding extra resources? Here are some resources you can use:");
        //Label label = new Label("Do you need help finding extra resources? Here are some resources you can use:");
        add(paragraph);

        Anchor link1 = new Anchor("https://www.khanacademy.org/", "Khan Academy");
        add(link1);

        Anchor link2 = new Anchor("https://www.quizlet.com/", "Quizlet");
        add(link2);

        Anchor link3 = new Anchor("https://www.collegeboard.org/", "Collegeboard");
        add(link3);
    }
}


