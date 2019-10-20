package com.contacts.app.repository;

import com.contacts.app.model.Role;
import com.contacts.app.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface to manage the role table operations
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    public Role findByName(RoleName name);
}
