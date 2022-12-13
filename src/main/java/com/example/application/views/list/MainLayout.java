package com.example.application.views.list;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightCondition;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Vaadin CRM");
        logo.addClassName("text-l");
        logo.addClassName("m-m");
       HorizontalLayout header = new HorizontalLayout(new DrawerToggle(),logo);
       header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
       header.expand(logo);
       header.setSizeFull();
       header.addClassName("py-0");
        header.addClassName("px-m");
        addToNavbar(header);
    }

    private void createDrawer() {

     RouterLink listView = new RouterLink("Link", ListView.class);
     listView.setHighlightCondition(HighlightConditions.sameLocation());
     addToDrawer(new VerticalLayout(listView));


    }
}
