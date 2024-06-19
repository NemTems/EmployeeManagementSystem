package com.system.EmployeeManagementSystem.controllers;


import com.system.EmployeeManagementSystem.DTOs.TaskDTO;
import com.system.EmployeeManagementSystem.services.implementation.TaskServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl idCardService) {
        this.taskService = idCardService;
    }

    @GetMapping("/showAll")
    public ResponseEntity<List<TaskDTO>> getAllTasks(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return new ResponseEntity<>(taskService.getAllTasks(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable int id) {
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTask(@RequestBody TaskDTO taskDTO) {
        return new ResponseEntity<>(taskService.createTask(taskDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<String> updateTask(@PathVariable int id, @RequestBody TaskDTO taskDTO) {
        return new ResponseEntity<>(taskService.updateTask(taskDTO, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        taskService.deleteTaskById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
