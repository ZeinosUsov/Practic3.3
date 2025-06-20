package com.example.projectdemex.controller;

import com.example.projectdemex.model.Complaint;
import com.example.projectdemex.service.ComplaintService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ComplaintControllerTest {

    @Mock
    private ComplaintService complaintService;

    @InjectMocks
    private ComplaintController complaintController;

    private MockMvc mockMvc;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(complaintController).build();
    }
    

    @Test
    void createComplaint_shouldRedirectToComplaintView() throws Exception {
        // Arrange
        String description = "Test Complaint";

        // Act & Assert
        mockMvc.perform(post("/complaint/create")
                        .param("description", description))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/complaint"));

        // Assert that createComplaint was called with a Complaint object having the given description
        verify(complaintService, times(1)).createComplaint(any(Complaint.class));
        verify(complaintService).createComplaint(argThat(complaint -> complaint.getDescription().equals(description) && complaint.getStatus().equals("OPEN")));
    }

    @Test
    void deleteComplaint_shouldRedirectToComplaintView() throws Exception {
        // Arrange
        Long complaintId = 1L;

        // Act & Assert
        mockMvc.perform(post("/complaint/delete/{id}", complaintId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/complaint"));

        verify(complaintService, times(1)).deleteComplaint(complaintId);
    }
}