package com.example.spring_security.repository;

import com.example.spring_security.domaine.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserApp, Long> {
    UserApp findByUsername(String username);
}
