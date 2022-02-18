package com.example.spring_security.api;

import com.example.spring_security.domaine.Role;
import com.example.spring_security.domaine.UserApp;
import com.example.spring_security.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserApp>>getUser(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user")
    public ResponseEntity<UserApp>saveUser(@RequestBody UserApp user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role")
    public ResponseEntity<Role>saveUser(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/user")
    public ResponseEntity<?>saveRoleToUser(@RequestBody RoleToUserForm roleToUser){
        userService.addRoleToUser(roleToUser.getUsername(), roleToUser.getRolename());
        return ResponseEntity.ok().build();
    }

}

@Data
class RoleToUserForm{
    private String username;
    private String rolename;
}