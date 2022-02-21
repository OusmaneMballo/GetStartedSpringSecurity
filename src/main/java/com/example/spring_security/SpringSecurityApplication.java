package com.example.spring_security;

import com.example.spring_security.domaine.Role;
import com.example.spring_security.domaine.UserApp;
import com.example.spring_security.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }


    @Bean
    CommandLineRunner run(UserService userService){
          return null; /*args -> {
              userService.saveRole(new Role(null, "ROLE_USER"));
              userService.saveRole(new Role(null, "ROLE_MANAGER"));
              userService.saveRole(new Role(null, "ROLE_ADMIN"));
              userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

              userService.saveUser(new UserApp(null, "John Travolta", "john", "1234", new ArrayList<>()));
              userService.saveUser(new UserApp(null, "Ousmane Mballo", "mballoS", "1234", new ArrayList<>()));
              userService.saveUser(new UserApp(null, "Will Smith", "will", "1234", new ArrayList<>()));
              userService.saveUser(new UserApp(null, "Arnold Schwarzenegger", "arnold", "1234", new ArrayList<>()));

              userService.addRoleToUser("mballoS", "ROLE_SUPER_ADMIN");
              userService.addRoleToUser("mballoS", "ROLE_ADMIN");
              userService.addRoleToUser("mballoS", "ROLE_USER");
              userService.addRoleToUser("john", "ROLE_USER");
              userService.addRoleToUser("john", "ROLE_ADMIN");
              userService.addRoleToUser("will", "ROLE_USER");
              userService.addRoleToUser("arnold", "ROLE_USER");
          };*/
    }
}
