package com.example.projectdemex.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ComplaintTest {

    @Test
    void testNoArgsConstructor() {
        Complaint complaint = new Complaint();
        assertNull(complaint.getId());
        assertNull(complaint.getTitle());
        assertNull(complaint.getDescription());
        assertNull(complaint.getStatus());
    }

    @Test
    void testAllArgsConstructor() {
        Complaint complaint = new Complaint("Title", "Description", "OPEN");
        assertEquals("Title", complaint.getTitle());
        assertEquals("Description", complaint.getDescription());
        assertEquals("OPEN", complaint.getStatus());
    }

    @Test
    void testGettersAndSetters() {
        Complaint complaint = new Complaint();
        complaint.setId(1L);
        complaint.setTitle("New Title");
        complaint.setDescription("New Description");
        complaint.setStatus("RESOLVED");

        assertEquals(1L, complaint.getId());
        assertEquals("New Title", complaint.getTitle());
        assertEquals("New Description", complaint.getDescription());
        assertEquals("RESOLVED", complaint.getStatus());
    }
}