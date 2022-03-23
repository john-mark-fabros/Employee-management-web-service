package com.johnmark.case_study.security.repo;

import com.johnmark.case_study.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * John Mark A. Fabros
 */
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}