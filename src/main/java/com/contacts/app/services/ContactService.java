package com.contacts.app.services;

import com.contacts.app.dto.ContactDTO;
import com.contacts.app.exceptions.contact.ContactNotFoundException;
import com.contacts.app.exceptions.contact.ContactParameterNull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface to manage the contact service
 */
@Service
public interface ContactService {
    public ContactDTO create(ContactDTO contact) throws ContactParameterNull;
    public ContactDTO update(Integer id, ContactDTO contactDTO) throws ContactNotFoundException, ContactParameterNull ;
    public void delete(Integer id);
    public List<ContactDTO> getByUser(String nickName) throws ContactNotFoundException ;
}
