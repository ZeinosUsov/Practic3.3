package com.example.projectdemex.service;

import com.example.projectdemex.model.Author;
import com.example.projectdemex.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void getAllAuthors_shouldReturnListOfAuthors() {

        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Doe", "John", ""));
        authors.add(new Author("Smith", "Jane", ""));

        when(authorRepository.findAll()).thenReturn(authors);


        List<Author> result = authorService.getAllAuthors();


        assertEquals(2, result.size());
        assertEquals("Doe", result.get(0).getLastName());
        assertEquals("Smith", result.get(1).getLastName());

        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void createAuthor_shouldSaveAuthor() {

        Author author = new Author("Doe", "John", "");
        when(authorRepository.save(author)).thenReturn(author);


        Author result = authorService.createAuthor(author);

        assertEquals("Doe", result.getLastName());
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    void deleteAuthor_shouldDeleteAuthorById() {

        Long authorId = 1L;
        doNothing().when(authorRepository).deleteById(authorId);

        authorService.deleteAuthor(authorId);

        verify(authorRepository, times(1)).deleteById(authorId);
    }
}