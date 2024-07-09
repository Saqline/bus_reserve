package com.example.reservation.security;

import com.example.reservation.entities.AppUsers;
import com.example.reservation.repos.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseLoader(AppUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... strings) {
        // Check if user already exists
        if (repository.findByUserName("admin").isEmpty()) {
            // Create new user
            AppUsers user = new AppUsers();
            user.setUserName("admin");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setRole("ADMIN");
            repository.save(user);
        }
    }
}