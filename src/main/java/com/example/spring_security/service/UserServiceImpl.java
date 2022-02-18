package com.example.spring_security.service;

import com.example.spring_security.domaine.Role;
import com.example.spring_security.domaine.UserApp;
import com.example.spring_security.repository.RoleRepository;
import com.example.spring_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public UserApp saveUser(UserApp user) {
        log.info("Saving new user {} to the database", user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        UserApp user=userRepository.findByUsername(username);
        Role role=roleRepository.findByName(roleName);
        if (user!=null && role!=null){
            log.info("Adding role {} to user {}", role.getName(), user.getUsername());
            user.getRoles().add(role);
        }
    }

    @Override
    public UserApp getUser(String username) {
        log.info("Getting user {} ", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserApp> getUsers() {
        log.info("Getting all users");
        return userRepository.findAll();
    }
}
