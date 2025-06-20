package com.example.projectdemex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/admin/users")
    public String userManagement() {
        return "admin/users";
    }

    @GetMapping("/admin/settings")
    public String adminSettings() {
        return "admin/settings";
    }
}