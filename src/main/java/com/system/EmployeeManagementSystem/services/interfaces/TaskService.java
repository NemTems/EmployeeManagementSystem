package com.system.EmployeeManagementSystem.services.interfaces;

import com.system.EmployeeManagementSystem.DTOs.TaskDTO;

import java.util.List;

public interface TaskService {
    List<TaskDTO> getAllTasks(int pageNo, int pageSize);

    TaskDTO getTaskById(int id);

    String createTask(TaskDTO taskDTO);

    String updateTask(TaskDTO taskDTO, int id);

    void deleteTaskById(int id);
}
