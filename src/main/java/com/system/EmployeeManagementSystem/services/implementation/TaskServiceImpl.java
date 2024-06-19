package com.system.EmployeeManagementSystem.services.implementation;

import com.system.EmployeeManagementSystem.DTOs.TaskDTO;
import com.system.EmployeeManagementSystem.exceptions.APIRequestException;
import com.system.EmployeeManagementSystem.mapper.TaskDTOMapper;
import com.system.EmployeeManagementSystem.models.Task;
import com.system.EmployeeManagementSystem.repositories.TaskRepository;
import com.system.EmployeeManagementSystem.services.interfaces.TaskService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskDTO> getAllTasks(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        return taskRepository.findAll(pageable).getContent().stream()
                .map(TaskDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTaskById(int id) {
        return taskRepository.findById(id).map(TaskDTOMapper::toDTO)
                .orElseThrow(() -> new APIRequestException("Task cannot be found by provided id"));
    }

    @Override
    public String createTask(TaskDTO taskDTO) {
        try {
            taskRepository.save(TaskDTOMapper.toEntity(taskDTO));
            return "Saved";
        }
        catch (Exception e){
            throw new APIRequestException("Task cannot be saved", e);
        }
    }

    @Override
    public String updateTask(TaskDTO taskDTO, int id) {
        Task foundTask = taskRepository.findById(id)
                .orElseThrow(() -> new APIRequestException("Task cannot be found by provided id"));

        foundTask.setDescription(taskDTO.getDescription());
        foundTask.setStatus(taskDTO.getStatus());
        foundTask.setProject(TaskDTOMapper.ProjectToEntity(taskDTO.getProject()));

        return "Updated";
    }

    @Override
    public void deleteTaskById(int id) {
        try {
            taskRepository.deleteById(id);
        }
        catch (Exception e){
            throw new APIRequestException("Task cannot be deleted or found by provided id", e);
        }
    }

}
