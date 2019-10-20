package com.contacts.app.repository;

import com.contacts.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface to manage the user table operations
 */
@Repository
public interface UserRepository  extends CrudRepository<User, Integer> {
    public User findByUsername(String username);
    public Boolean existsByUsername(String username);
    public Boolean existsByEmail(String email);
}
