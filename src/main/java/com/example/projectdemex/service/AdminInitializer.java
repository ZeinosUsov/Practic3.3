package com.example.projectdemex.service;

import com.example.projectdemex.model.Role;
import com.example.projectdemex.model.User;
import com.example.projectdemex.repo.UserRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Profile("!test") // Не будет выполняться во время тестов
public class AdminInitializer {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminInitializer(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initAdminUser() {
        if (userRepo.findByUsername("admin") == null) {
            User admin = new User();
            admin.setName("Admin");
            admin.setSurname("Adminov");
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@example.com");
            admin.setPhone("+1234567890");
            admin.setRoles(Collections.singleton(Role.ADMIN));
            admin.setActivationCode(null);

            userRepo.save(admin);
            System.out.println("Администратор создан. Логин: admin, Пароль: admin123");
        }
    }
}