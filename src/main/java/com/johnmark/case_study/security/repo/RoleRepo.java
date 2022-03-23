package com.johnmark.case_study.security.repo;

import com.johnmark.case_study.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * John Mark A. Fabros
 */
public interface RoleRepo extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
