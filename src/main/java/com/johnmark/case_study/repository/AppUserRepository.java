package com.johnmark.case_study.repository;

import com.johnmark.case_study.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * John Mark A. Fabros
 */
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    AppUser findAppUserByName(String name);
}
