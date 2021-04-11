package com.nameless1620.gradecalc.ui.views.resources;

import com.nameless1620.gradecalc.ui.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("GradeCalc | Resources")
@Route(value = "resources", layout = MainLayout.class)
public class ResourcesView extends VerticalLayout {

    public ResourcesView(){

    }
}
// Link link = new link( "Click Me!,
//            new ExternalResource("http;//vaadin.com/")
/*
This is used to open a URL in a new tab

link.setIcon(new ThemeResource("icons/external-link.png"));
link.addStyleName("icon-after-caption");
 */
