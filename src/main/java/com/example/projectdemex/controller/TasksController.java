package com.example.projectdemex.controller;

import com.example.projectdemex.dto.TaskDto;
import com.example.projectdemex.service.TaskService;
import com.example.projectdemex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/tasks")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class TasksController {

    private final TaskService taskService;
    private final UserService userService;

    @GetMapping("/list")
    String findAllTasks(Model model) {
        model.addAttribute("users", userService.findAllUser());
        model.addAttribute("tasks", taskService.findAllTask());
        model.addAttribute("task", new TaskDto());
        return "admin/tasks_list";
    }

    @PostMapping("/addTask")
    String addNewTask(@ModelAttribute TaskDto taskDto){
        taskService.save(taskDto);
        return "redirect:/admin/tasks/list";
    }
}