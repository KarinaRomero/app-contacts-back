package com.contacts.app.repository;
import com.contacts.app.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface to manage the contact table operations
 */
public interface ContactRepository extends JpaRepository<Contact, String> {
    public List<Contact> findAllByUser(String idUser);
    public List<Contact> findAllByUserAndNickName(String idUser, String nickName);
    public void deleteByIdContact(String idContact);
}
