package com.contacts.app.services.impl;

import com.contacts.app.dto.ContactDTO;
import com.contacts.app.exceptions.contact.ContactNotFoundException;
import com.contacts.app.model.Contact;
import com.contacts.app.repository.ContactRepository;
import com.contacts.app.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to manage the contact service
 */
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    /**
     * Constructor to initialize
     * @param contactRepository manage the contact table operations
     */
    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /**
     * Method to create a new contact
     * @param contact to create
     * @return contact or null if it could not be created
     */
    @Override
    public ContactDTO create(ContactDTO contact) {
        return null;
    }
    /**
     * Method to update a contact
     * @param contact to edit
     * @return contact or null if it could not be updated
     */
    @Override
    public ContactDTO update(String id, ContactDTO contact) {
        return null;
    }
    /**
     * Method to delete a contact
     * @param id to delete
     */
    @Override
    public void delete(String id) {}
    /**
     * Method to get a list of contacts by user
     * @param idUser to find
     * @return contact list or null if it could not be found
     */
    @Override
    public List<ContactDTO> getByUser(String idUser) {
        return null;
    }

    /**
     * Method to convert a list of contacts object to DTO objects
     * @param contactModelList to convert
     * @return contactDTO object
     */
    private List<ContactDTO> convertToDTO(List<Contact> contactModelList) {
        return contactModelList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Method to convert a contact object to DTO object
     * @param contactModel to convert
     * @return contactDTO object
     */
    private ContactDTO convertToDTO(Contact contactModel) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setIdContact(contactModel.getIdContact());
        contactDTO.setName(contactModel.getName());
        contactDTO.setAge(contactModel.getAge());
        contactDTO.setNickName(contactModel.getNickName());
        contactDTO.setPhoneNumber(contactModel.getPhoneNumber());

        return contactDTO;
    }
}
