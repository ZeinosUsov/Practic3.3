package com.example.projectdemex.service;

import com.example.projectdemex.model.Complaint;
import com.example.projectdemex.repository.ComplaintRepository;
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
class ComplaintServiceTest {

    @Mock
    private ComplaintRepository complaintRepository;

    @InjectMocks
    private ComplaintService complaintService;

    @Test
    void getAllComplaints_shouldReturnListOfComplaints() {

        List<Complaint> complaints = new ArrayList<>();
        complaints.add(new Complaint("title", "description", "OPEN"));
        complaints.add(new Complaint("title2", "description2", "CLOSED"));

        when(complaintRepository.findAll()).thenReturn(complaints);


        List<Complaint> result = complaintService.getAllComplaints();


        assertEquals(2, result.size());
        assertEquals("title", result.get(0).getTitle());
        assertEquals("title2", result.get(1).getTitle());

        verify(complaintRepository, times(1)).findAll();
    }

    @Test
    void createComplaint_shouldSaveComplaint() {

        Complaint complaint = new Complaint("title", "description", "OPEN");
        when(complaintRepository.save(complaint)).thenReturn(complaint);


        Complaint result = complaintService.createComplaint(complaint);


        assertEquals("title", result.getTitle());
        verify(complaintRepository, times(1)).save(complaint);
    }

    @Test
    void deleteComplaint_shouldDeleteComplaintById() {

        Long complaintId = 1L;
        doNothing().when(complaintRepository).deleteById(complaintId);


        complaintService.deleteComplaint(complaintId);


        verify(complaintRepository, times(1)).deleteById(complaintId);
    }
}