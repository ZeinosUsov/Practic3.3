package com.example.projectdemex.controller;

import com.example.projectdemex.dto.UserDto;
import com.example.projectdemex.impl.UserServiceImpl;
import com.example.projectdemex.model.Author;
import com.example.projectdemex.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userServiceImpl;
    private final AuthorService authorService;

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("userDto") UserDto userDto) {
        return "registration";
    }

    @PostMapping("/registration")
    public String saveUser(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (userServiceImpl.isUsernameAvailable(userDto.getUsername())) {
            bindingResult.rejectValue("username", "error.username", "Имя пользователя уже занято!");
            return "registration";
        }

        if (userServiceImpl.isEmailAvailable(userDto.getEmail())) {
            bindingResult.rejectValue("email", "error.email", "Почта уже занята!");
            return "registration";
        }

        userServiceImpl.save(userDto);
        return "redirect:/login";
    }

    @GetMapping("/manage_authors")
    public String getAllAuthors(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "manage_authors";
    }

    @GetMapping("/manage_authors/add")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "add_author";
    }

    @PostMapping("/manage_authors/create")
    public String createAuthor(@ModelAttribute Author author) {
        authorService.createAuthor(author);
        return "redirect:/manage_authors";
    }

    @PostMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/manage_authors";
    }
}