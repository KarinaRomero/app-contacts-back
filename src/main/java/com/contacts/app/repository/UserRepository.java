package com.contacts.app.repository;

import com.contacts.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface to manage the user table operations
 */
public interface UserRepository  extends JpaRepository<User, String> {
    public User findByIdUser(String id);
}
