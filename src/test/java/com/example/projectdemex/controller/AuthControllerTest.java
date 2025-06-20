package com.example.projectdemex.controller;

import com.example.projectdemex.impl.UserServiceImpl;
import com.example.projectdemex.model.Author;
import com.example.projectdemex.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private UserServiceImpl userServiceImpl;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }


    @Test
    void showAddAuthorForm_shouldReturnAddAuthorView() throws Exception {
        mockMvc.perform(get("/manage_authors/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add_author"))
                .andExpect(model().attributeExists("author"));
    }

    @Test
    void createAuthor_shouldRedirectToManageAuthors() throws Exception {
        Author author = new Author();
        author.setName("Test Author"); // Set a name to avoid validation issues

        mockMvc.perform(post("/manage_authors/create")
                        .flashAttr("author", author))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/manage_authors"));

        verify(authorService, times(1)).createAuthor(author);
    }

    @Test
    void deleteAuthor_shouldRedirectToManageAuthors() throws Exception {
        Long authorId = 1L;

        mockMvc.perform(post("/delete/{id}", authorId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/manage_authors"));

        verify(authorService, times(1)).deleteAuthor(authorId);
    }

}