package com.contacts.app.repository;
import com.contacts.app.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface to manage the contact table operations
 */
@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    @Query("SELECT c FROM Contact c  WHERE  c.user.idUser = ?1")
    public List<Contact> findAllByUser(Integer idUser);
    //public List<Contact> findAllByUserAndNickName(Integer idUser, String nickName);
    public Contact findByIdContact(Integer id);
    public void deleteByIdContact(Integer idContact);
}
