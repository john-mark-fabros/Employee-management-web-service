package com.johnmark.case_study.controller;

import com.johnmark.case_study.model.AppUser;
import com.johnmark.case_study.model.UserRole;
import com.johnmark.case_study.service.AppUserServiceImplementation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * John Mark A. Fabros
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserResource {

    private final AppUserServiceImplementation serviceImplementation;

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getUsers() {
        return ResponseEntity.ok().body(serviceImplementation.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(serviceImplementation.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<UserRole> saveRole(@RequestBody UserRole role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(serviceImplementation.saveUserRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addUserRole(@RequestBody RoleToUserForm form) {
        serviceImplementation.addRoleToUser(form.getName(), form.getRole());
        return ResponseEntity.ok().build();
    }

}

@Data
class RoleToUserForm {
    private String name;
    private String role;
}

