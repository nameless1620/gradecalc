package com.nameless1620.gradecalc.ui;

import com.nameless1620.gradecalc.ui.views.calculator.CalculatorView;
import com.nameless1620.gradecalc.ui.views.dashboard.DashboardView;
import com.nameless1620.gradecalc.ui.views.list.ListView;
import com.nameless1620.gradecalc.ui.views.resources.ResourcesView;
import com.nameless1620.gradecalc.ui.views.sandbox.AryaSandboxView;
import com.nameless1620.gradecalc.ui.views.sandbox.BhoomiSandboxView;
import com.nameless1620.gradecalc.ui.views.sandbox.IsabellaSandboxView;
import com.nameless1620.gradecalc.ui.views.sandbox.JoshikaSandboxView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
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
        RouterLink listLink = new RouterLink("List", ListView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                listLink,
                new RouterLink("Dashboard", DashboardView.class),
                new RouterLink("Resources", ResourcesView.class),
                new RouterLink("Calculator", CalculatorView.class),
                new RouterLink("Arya Sandbox", AryaSandboxView.class),
                new RouterLink("Joshika Sandbox", JoshikaSandboxView.class),
                new RouterLink("Bhoomi Sandbox", BhoomiSandboxView.class),
                new RouterLink("Isabella Sandbox", IsabellaSandboxView.class)
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
