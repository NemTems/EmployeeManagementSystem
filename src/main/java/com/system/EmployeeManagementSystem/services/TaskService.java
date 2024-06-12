package com.system.EmployeeManagementSystem.services;

import com.system.EmployeeManagementSystem.DTOs.TaskDTO;
import com.system.EmployeeManagementSystem.mapper.TaskDTOMapper;
import com.system.EmployeeManagementSystem.models.Task;
import com.system.EmployeeManagementSystem.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskDTO> getAll() {
        return taskRepository.findAll().stream()
                .map(TaskDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TaskDTO getById(int id) {
        return taskRepository.findById(id).map(TaskDTOMapper::toDTO).get();
    }

    public Task save(Task Task) {
        return taskRepository.save(Task);
    }

    public Task update(Task Task) {
        return taskRepository.save(Task);
    }

    public void delete(int id) {
        taskRepository.deleteById(id);
    }

}
