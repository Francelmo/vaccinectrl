package com.francelmofarias.vaccinectrl.repository;

import com.francelmofarias.vaccinectrl.model.Role;
import com.francelmofarias.vaccinectrl.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}

