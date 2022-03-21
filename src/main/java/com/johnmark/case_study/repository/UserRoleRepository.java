package com.johnmark.case_study.repository;

import com.johnmark.case_study.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * John Mark A. Fabros
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    UserRole findUserRoleByName(String name);
}
