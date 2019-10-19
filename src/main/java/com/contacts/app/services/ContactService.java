package com.contacts.app.services;

import com.contacts.app.dto.ContactDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface to manage the contact service
 */
@Service
public interface ContactService {
    public ContactDTO create(ContactDTO contact);
    public ContactDTO update(String id, ContactDTO contact);
    public void delete(String id);
    public List<ContactDTO> getByUser(String idUser);
}