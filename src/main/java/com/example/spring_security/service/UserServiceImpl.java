package com.example.spring_security.service;

import com.example.spring_security.domaine.Role;
import com.example.spring_security.domaine.UserApp;
import com.example.spring_security.repository.RoleRepository;
import com.example.spring_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp userApp = userRepository.findByUsername(username);
        if (userApp==null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User note found");
        }
        else {
            log.info("User found in the database {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userApp.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(userApp.getUsername(), userApp.getPassword(), authorities);
    }

    @Override
    public UserApp saveUser(UserApp user) {
        log.info("Saving new user {} to the database", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
