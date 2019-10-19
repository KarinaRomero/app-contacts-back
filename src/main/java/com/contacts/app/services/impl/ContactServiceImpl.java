package com.contacts.app.services;

import com.contacts.app.model.Contact;
import com.contacts.app.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class to manage the contact service
 */
@Service
public class ContactService {

    private final ContactRepository contactRepository;

    /**
     * Constructor to initialize
     * @param contactRepository manage the contact table operations
     */
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /**
     * Method to create a new contact
     * @param contact to create
     * @return contact or null if it could not be created
     */
    public Contact create(Contact contact) {
        return this.contactRepository.save(contact);
    }

}
