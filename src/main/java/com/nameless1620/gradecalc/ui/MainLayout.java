package com.nameless1620.gradecalc.ui;

import com.nameless1620.gradecalc.ui.views.gradecalculator.GradeCalculatorView;
import com.nameless1620.gradecalc.ui.views.resources.ResourcesView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Router;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;

@PWA(
        name = "Grade Calc",
        shortName = "Grades",
        offlineResources = {
                "./styles/offline.css",
                "./images/offline.png"
        }
//        ,enableInstallPrompt = false // toggle intrusive PWA install prompt
)
@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createDrawer() {
        RouterLink calcLink = new RouterLink("Grade Calculator", GradeCalculatorView.class);
        calcLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink resourceLink = new RouterLink("Resources", ResourcesView.class);
        calcLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
              calcLink,
              resourceLink
        ));
    }

    private void createHeader() {
        H1 logo = new H1("Grade Calc");
        logo.addClassName("logo");

        Anchor logOut = new Anchor("/logout", "Log out");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logOut);
        header.addClassName("header");
        header.setWidth("100%");
        header.expand(logo); // push logOut to the right
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        addToNavbar(header);
    }

}
