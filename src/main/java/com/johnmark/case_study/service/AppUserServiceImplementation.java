package com.johnmark.case_study.service;

import com.johnmark.case_study.model.AppUser;
import com.johnmark.case_study.model.UserRole;
import com.johnmark.case_study.repository.AppUserRepository;
import com.johnmark.case_study.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
@Slf4j
public class AppUserServiceImplementation implements AppUserService, UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
       AppUser user = appUserRepository.findAppUserByName(name);
       if(user == null) {
           log.error("[Load user by name] user does not exist", name);
           throw new UsernameNotFoundException("user does not exist"+ name);
       }
        Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
       user.getUserRoles().forEach(userRole -> {
           simpleGrantedAuthorities.add(new SimpleGrantedAuthority(userRole.getName()));
       });
       return new User(user.getName(), user.getPassword(), simpleGrantedAuthorities);
    }

    @Override
    public AppUser saveUser(AppUser appUser) {
        log.info("saving user --> ", appUser);
        return appUserRepository.save(appUser);
    }

    @Override
    public UserRole saveUserRole(UserRole userRole) {
        log.info("saving user role--> ", userRole);
        return userRoleRepository.save(userRole);
    }

    @Override
    public void addRoleToUser(String name, String role) {
        log.info("adding role to user");
        AppUser user = appUserRepository.findAppUserByName(name);
        UserRole roles = userRoleRepository.findUserRoleByName(role);
        user.getUserRoles().add(roles);
    }

    @Override
    public AppUser getUser(String user) {
        log.info("fetching user --> ", user);
        return appUserRepository.findAppUserByName(user);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("list of all user");
        return appUserRepository.findAll();
    }


}

