package com.example.application.views.list;

import com.example.application.data.entity.Contact;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collection;
import java.util.Collections;


@PageTitle("Contacts | Vaadin CRM")
@Route(value = "")
public class ListView extends VerticalLayout {
    Grid<Contact> grid = new Grid<>(Contact.class);
    TextField filter = new TextField();

    ContactForm contactForm;

    public ListView() {
       addClassName("list-view");
       setSizeFull();
       configureGrid();
       configureForm();
       add(getToolBar(),getContent());
    }

    private Component getContent() {
        return null;
    }

    private void configureForm() {
        contactForm = new ContactForm(Collections.emptyList(),Collections.emptyList());
        contactForm.setWidth("25em");
    }

    private Component getToolBar() {
        filter.setPlaceholder("Filter by name.....");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        Button addContact = new Button("Add Contact");
        HorizontalLayout toolBar = new HorizontalLayout(filter,addContact);
        return toolBar;
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName","lastName","email");
        grid.addColumn(column -> column.getCompany().getName()).setHeader("Status");
        grid.addColumn(column -> column.getCompany().getName()).setHeader("Company");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        
    }

}
