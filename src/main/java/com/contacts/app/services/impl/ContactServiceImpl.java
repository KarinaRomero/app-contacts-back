package com.contacts.app.services.impl;

import com.contacts.app.dto.ContactDTO;
import com.contacts.app.exceptions.contact.ContactNotFoundException;
import com.contacts.app.exceptions.contact.ContactParameterNull;
import com.contacts.app.exceptions.user.UserNotFound;
import com.contacts.app.model.Contact;
import com.contacts.app.model.User;
import com.contacts.app.repository.ContactRepository;
import com.contacts.app.repository.UserRepository;
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
    private final UserRepository userRepository;

    /**
     * Constructor to initialize
     * @param contactRepository manage the contact table operations
     */
    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    /**
     * Method to create a new contact
     * @param contactDTO to create
     * @return contact or null if it could not be created
     */
    @Override
    public ContactDTO create(ContactDTO contactDTO) throws ContactParameterNull {
        User user = this.userRepository.findByUsername(contactDTO.getUser().getUsername());
        if(contactDTO.getName() == null) {
            throw new ContactParameterNull("Name cannot be null");
        }
        if(contactDTO.getAge() == 0) {
            throw new ContactParameterNull("Age cannot be null");
        }
        if(contactDTO.getPhoneNumber() == null) {
            throw new ContactParameterNull("Phone number cannot be null");
        }
        Contact contactTemp = convertToContact(contactDTO);
        contactTemp.setUser(user);
        Contact contact = this.contactRepository.save(contactTemp);

        return this.convertToDTO(contact);
    }
    /**
     * Method to update a contact
     * @param contactDTO to edit
     * @return contact or null if it could not be updated
     */
    @Override
    public ContactDTO update(Integer id, ContactDTO contactDTO) throws ContactNotFoundException, ContactParameterNull {
        Contact contact = this.contactRepository.findByIdContact(id);
        if(contactDTO.getName() == null) {
            throw new ContactParameterNull("Name cannot be null");
        }
        if(contactDTO.getAge() == 0) {
            throw new ContactParameterNull("Age cannot be null");
        }
        if(contactDTO.getPhoneNumber() == null) {
            throw new ContactParameterNull("Phone number cannot be null");
        }
        if(contact == null)
        {
            throw new ContactNotFoundException("Contact id " + id + "is not register");
        }
        contact.setName(contactDTO.getName());
        contact.setAge(contactDTO.getAge());
        contact.setNickName(contactDTO.getNickName());
        contact.setPhoneNumber(contactDTO.getPhoneNumber());

        Contact contactTemp = this.contactRepository.save(contact);

        return this.convertToDTO(contactTemp);
    }
    /**
     * Method to delete a contact
     * @param id to delete
     */
    @Override
    public void delete(Integer id) {
        Contact contact = this.contactRepository.findByIdContact(id);
        if(contact!=null) {
            this.contactRepository.deleteById(id);
        }
    }
    /**
     * Method to get a list of contacts by user
     * @param nickName to find
     * @return contact list or null if it could not be found
     */
    @Override
    public List<ContactDTO> getByUser(String nickName) throws ContactNotFoundException {
        int idUser = this.userRepository.findByUsername(nickName).getIdUser();
        if(this.contactRepository.findAllByUser(idUser) == null) {
            throw new ContactNotFoundException("User "+ idUser +"not found" );
        }
        List<ContactDTO> contactDTOS = this.convertToDTO(this.contactRepository.findAllByUser(idUser));
        return contactDTOS;
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
        if(contactModel.getIdContact() != null) {
            contactDTO.setIdContact(contactModel.getIdContact());
        }
        contactDTO.setName(contactModel.getName());
        contactDTO.setAge(contactModel.getAge());
        contactDTO.setNickName(contactModel.getNickName());
        contactDTO.setPhoneNumber(contactModel.getPhoneNumber());

        return contactDTO;
    }
    /**
     * Method to convert a contact object to DTO object
     * @param contactDTO to convert
     * @return contact object model
     */
    private Contact convertToContact(ContactDTO contactDTO) {
        Contact contact = new Contact();
        if(contactDTO.getIdContact()!= null) {
            contact.setIdContact(contactDTO.getIdContact());
        }
        contact.setName(contactDTO.getName());
        contact.setAge(contactDTO.getAge());
        contact.setNickName(contactDTO.getNickName());
        contact.setPhoneNumber(contactDTO.getPhoneNumber());

        return contact;
    }
}
