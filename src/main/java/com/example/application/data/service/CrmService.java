package com.example.application.data.service;

import com.example.application.data.entity.Company;
import com.example.application.data.entity.Contact;
import com.example.application.data.entity.Status;
import com.example.application.data.repository.CompanyRepository;
import com.example.application.data.repository.ContactRepository;
import com.example.application.data.repository.StatusRepository;

import java.util.List;

public class CrmService {
    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;
    private final StatusRepository statusRepository;

    public CrmService(ContactRepository contactRepository, CompanyRepository companyRepository,
                      StatusRepository statusRepository) {
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
        this.statusRepository = statusRepository;
    }

    public long countContact(){
        return contactRepository.count();
    }

    public List<Contact> findAllContacts(String filterText){
        if(filterText == null || filterText.isEmpty()){
            return contactRepository.findAll();
        }
        else{
            return contactRepository.search(filterText);
        }
    }
    public void deleteContact(Contact contact){
        contactRepository.delete(contact);
    }

    public void saveContact(Contact contact){
        if(contact == null){
            System.err.println("contact is null");
            return;
        }
        contactRepository.save(contact);
    }
    public List<Company> findAllCompanies(){
       return companyRepository.findAll();
    }

    public  List<Status> findAllStatuses(){
        return statusRepository.findAll();
    }
}