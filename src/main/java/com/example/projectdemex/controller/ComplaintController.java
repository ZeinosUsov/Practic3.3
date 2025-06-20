package com.example.projectdemex.controller;

import com.example.projectdemex.model.Complaint;
import com.example.projectdemex.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @GetMapping("/complaint")
    public String getAllComplaints(Model model) {
        List<Complaint> complaints = complaintService.getAllComplaints();
        model.addAttribute("complaint", complaints);
        return "complaint";
    }

    @PostMapping("/complaint/create")
    public String createComplaint(@RequestParam String description) {
        Complaint complaint = new Complaint();
        complaint.setDescription(description);
        complaint.setStatus("OPEN");
        complaintService.createComplaint(complaint);
        return "redirect:/complaint";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/complaint/delete/{id}")
    public String deleteComplaint(@PathVariable("id") Long id) {
        complaintService.deleteComplaint(id);
        return "redirect:/complaint";
    }
}