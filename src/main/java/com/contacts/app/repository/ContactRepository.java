package com.contacts.app.repository;
import com.contacts.app.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface to manage the contact table operations
 */
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    public List<Contact> findAllByUser(Integer idUser);
    public List<Contact> findAllByUserAndNickName(Integer idUser, String nickName);
    public Contact findByIdContact(Integer id);
    public void deleteByIdContact(Integer idContact);
}
