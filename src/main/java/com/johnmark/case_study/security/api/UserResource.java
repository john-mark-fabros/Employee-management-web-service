package com.johnmark.case_study.security.api;

import com.johnmark.case_study.security.model.Role;
import com.johnmark.case_study.security.model.User;
import com.johnmark.case_study.security.service.UserService;
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
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>>getUser() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/create/user")
    public ResponseEntity<User>saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/admin/create/user").toString());
        return ResponseEntity.created(uri).body(userService.addUser(user));
    }

    @PostMapping("/save/role")
    public ResponseEntity<Role>saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/admin/save/role").toString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/add/role")
    public ResponseEntity<?>addRoleToUser(@RequestBody Form form) {
        userService.addRoleToUser(form.getUsername(), form.getRole());
        return ResponseEntity.ok().build();
    }

}

@Data
class Form {
    private String username;
    private String role;
}
