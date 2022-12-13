package com.example.application.views.list;

import com.example.application.data.entity.Contact;
import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Collections;


@PageTitle("Contacts | Vaadin CRM")
@Route(value = "", layout = MainLayout.class)
public class ListView extends VerticalLayout {
    Grid<Contact> grid = new Grid<>(Contact.class);
    TextField filter = new TextField();

    ContactForm contactForm;

    private CrmService service;

    public ListView(CrmService service) {
        this.service = service;
        addClassName("list-view");
       setSizeFull();
       configureGrid();
       configureForm();
       add(getToolBar(),getContent());
       
      updateList();
      closeEditor();
    }

    private void closeEditor() {
        contactForm.setContact(null);
        contactForm.setVisible(false);
        removeClassName("Editing");

    }

    private void updateList() {
        grid.setItems(service.findAllContacts(filter.getValue()));
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid,contactForm);
        content.setFlexGrow(2,grid);
        content.setFlexGrow(1,contactForm);
        content.addClassName("content");
        content.setSizeFull();
       return  content;
    }

    private void configureForm() {
        contactForm = new ContactForm(service.findAllCompanies(),service.findAllStatuses());
        contactForm.setWidth("25em");
        contactForm.addListener(ContactForm.SaveEvent.class, this::saveContact);
        contactForm.addListener(ContactForm.DeleteEvent.class, this::deleteContact);
        contactForm.addListener(ContactForm.CloseEvent.class, e -> closeEditor());


    }
    public void saveContact(ContactForm.SaveEvent event)
    {
        service.saveContact(event.getContact());
        updateList();
        closeEditor();
    }
    public void deleteContact(ContactForm.DeleteEvent event){
        service.deleteContact(event.getContact());
        updateList();
        closeEditor();
    }

    private Component getToolBar() {
        filter.setPlaceholder("Filter by name.....");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> updateList());
        Button addContact = new Button("Add Contact");
        addContact.addClickListener(e -> addContact());
        HorizontalLayout toolBar = new HorizontalLayout(filter,addContact);
        return toolBar;
    }

    private void addContact() {
        grid.asSingleSelect().clear();
        editContact(new Contact());
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName","lastName","email");
        grid.addColumn(column -> column.getCompany().getName()).setHeader("Status");
        grid.addColumn(column -> column.getCompany().getName()).setHeader("Company");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(e -> editContact(e.getValue()));

        
    }

    private void editContact(Contact contact) {
        if(contact ==null){
            closeEditor();
        }
        else{
            contactForm.setContact(contact);
            contactForm.setVisible(true);
            addClassName("Editing");
        }
    }

}
