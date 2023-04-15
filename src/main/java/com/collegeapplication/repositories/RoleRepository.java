package com.collegeapplication.repositories;

import com.collegeapplication.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String  name);
}
