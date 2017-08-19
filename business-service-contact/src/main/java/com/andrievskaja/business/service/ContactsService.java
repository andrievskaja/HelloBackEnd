package com.andrievskaja.business.service;

import com.andrievskaja.business.service.model.view.ContactView;
import java.util.List;

/**
 *
 * @author Людмила
 */
public interface ContactsService {

    List<ContactView> listContactsSort(String reg);
    List<ContactView> listContacts();
}
