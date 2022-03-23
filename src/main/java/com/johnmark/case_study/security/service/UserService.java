package com.johnmark.case_study.security.service;

import com.johnmark.case_study.security.model.Role;
import com.johnmark.case_study.security.model.User;

import java.util.List;

/**
 * John Mark A. Fabros
 */
public interface UserService {
    User addUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String role);
    User getUser(String username);
    void deleteUser(Integer id);
    List<User>getUsers();
}
