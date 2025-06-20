package com.example.projectdemex.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AuthorTest {

    @Test
    void testNoArgsConstructor() {
        Author author = new Author();
        assertNull(author.getId());
        assertNull(author.getLastName());
        assertNull(author.getName());
        assertNull(author.getSurname());
    }

    @Test
    void testAllArgsConstructor() {
        Author author = new Author("Doe", "John", "Middle");
        assertEquals("Doe", author.getLastName());
        assertEquals("John", author.getName());
        assertEquals("Middle", author.getSurname());
    }

    @Test
    void testGettersAndSetters() {
        Author author = new Author();
        author.setId(1L);
        author.setLastName("Smith");
        author.setName("Jane");
        author.setSurname("Ann");

        assertEquals(1L, author.getId());
        assertEquals("Smith", author.getLastName());
        assertEquals("Jane", author.getName());
        assertEquals("Ann", author.getSurname());
    }
}