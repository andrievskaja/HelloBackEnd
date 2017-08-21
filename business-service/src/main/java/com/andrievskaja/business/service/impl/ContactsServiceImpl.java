package com.andrievskaja.business.service.impl;

import com.andrievskaja.business.model.Contact;
import com.andrievskaja.business.service.ContactsService;
import com.andrievskaja.business.service.model.view.ContactView;
import com.andrievskaja.dao.ContactRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Людмила
 */
@Service("contactsService")
public class ContactsServiceImpl implements ContactsService {

    private final ModelMapper mapper = new ModelMapper();
    /**
     *
     * For work with database Mysql
     */
    @Autowired
    private ContactRepository contactRepository;

    /**
     *
     * test initialization database Mysql
     *
     * @return
     */
    public List<Contact> testListContacts() {
        List<Contact> contact = new ArrayList<>();
        contact.add(new Contact(1, "Ivan"));
        contact.add(new Contact(2, "Olga"));
        contact.add(new Contact(3, "Sacha"));
        contact.add(new Contact(4, "Anna"));
        contact.add(new Contact(5, "Alina"));
        contact.add(new Contact(6, "Vanja"));
        contact.add(new Contact(7, "Senja"));
        contact.add(new Contact(8, "Ava"));
        contact.add(new Contact(9, "Olli"));
        contact.add(new Contact(10, "Vasja"));
        return contact;
    }

    /**
     *
     * @param reg
     * @return sort contacts
     */
    @Override
    @Transactional(readOnly = true)
    public List<ContactView> listContactsSort(String reg) {
        List<Contact> contacts = contactRepository.findAll();
        List<ContactView> contactsView = new ArrayList<>();
        if (contacts == null) {
            return null;
        }
        Pattern p = Pattern.compile(reg);
        isRegex(reg);
        contacts.stream().forEach((contact) -> {
            Matcher m = p.matcher(contact.getName());
            if (m.matches()) {
                contactsView.add(mapper.map(contact, ContactView.class));
            }
        });
        return contactsView;

    }

    public boolean isRegex(final String str) {
        try {
            Pattern.compile(str);
            return true;
        } catch (PatternSyntaxException e) {
            throw new PatternSyntaxException("PatternSyntaxException",str,1); 
        }
    }

    /**
     *
     * @return all contacts
     */
//    @Override
//    @Transactional(readOnly = true)
//    public List<ContactView> listContacts() {
////        List<Contact> contacts = contactRepository.findAll(); - Will return all records from Mysql
//        List<ContactView> contactsView = new ArrayList<>();
//        testListContacts().stream().forEach((contact) -> {
//            contactsView.add(mapper.map(contact, ContactView.class));
//        });
//        return contactsView;
//    }
    @Override
    @Transactional(readOnly = true)
    public List<ContactView> listContacts() {
        List<Contact> contacts = contactRepository.findAll();
        List<ContactView> contactsView = new ArrayList<>();
        contacts
                .stream().forEach((contact) -> {
                    contactsView.add(mapper.map(contact, ContactView.class
                    ));
                });
        return contactsView;
    }
}
