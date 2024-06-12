package com.system.EmployeeManagementSystem.controllers;


import com.system.EmployeeManagementSystem.DTOs.TaskDTO;
import com.system.EmployeeManagementSystem.models.Task;
import com.system.EmployeeManagementSystem.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService idCardService) {
        this.taskService = idCardService;
    }

    @GetMapping("/showAll")
    public List<TaskDTO> getAllTasks() {
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable int id) {
        return taskService.getById(id);
    }

    @PostMapping("/add")
    public void addTask(@RequestBody Task department) {
        taskService.save(department);
    }

    @PutMapping("/update")
    public void updateTask(@RequestBody Task department) {
        taskService.update(department);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteTask(@PathVariable int id) {
        taskService.delete(id);
    }
}
