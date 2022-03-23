package com.johnmark.case_study.security.service;

import com.johnmark.case_study.security.model.Role;
import com.johnmark.case_study.security.model.User;
import com.johnmark.case_study.security.repo.RoleRepo;
import com.johnmark.case_study.security.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * John Mark A. Fabros
 */
@Service
@RequiredArgsConstructor
@Transactional

public class UserServiceImplementation implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User does not exist");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRole().forEach(role-> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepo.deleteById(id);
    }


    @Override
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        User user = userRepo.findByUsername(username);
        Role roles = roleRepo.findByName(role);
        user.getRole().add(roles);
    }

    @Override
    public User getUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }


}
