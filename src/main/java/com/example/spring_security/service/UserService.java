package com.example.spring_security.service;

import com.example.spring_security.domaine.Role;
import com.example.spring_security.domaine.UserApp;

import java.util.List;

public interface UserService {
    UserApp saveUser(UserApp user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    UserApp getUser(String username);
    List<UserApp> getUsers();
}
