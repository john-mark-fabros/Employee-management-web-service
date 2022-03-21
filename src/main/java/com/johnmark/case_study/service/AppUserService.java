package com.johnmark.case_study.service;

import com.johnmark.case_study.model.AppUser;
import com.johnmark.case_study.model.UserRole;

import java.util.List;

/**
 * John Mark A. Fabros
 */
public interface AppUserService {

    AppUser saveUser(AppUser appUser);
    UserRole saveUserRole(UserRole userRole);
    void addRoleToUser(String name, String role);
    AppUser getUser(String user);
    List<AppUser>getUsers();

}
