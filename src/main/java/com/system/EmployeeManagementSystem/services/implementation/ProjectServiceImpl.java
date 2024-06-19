package com.system.EmployeeManagementSystem.services.implementation;

import com.system.EmployeeManagementSystem.DTOs.ProjectDTO;
import com.system.EmployeeManagementSystem.exceptions.APIRequestException;
import com.system.EmployeeManagementSystem.mapper.ProjectDTOMapper;
import com.system.EmployeeManagementSystem.models.Project;
import com.system.EmployeeManagementSystem.repositories.ProjectRepository;
import com.system.EmployeeManagementSystem.services.interfaces.ProjectService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectDTO> getAllProjects(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        return projectRepository.findAll(pageable).getContent().stream()
                .map(ProjectDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO getProjectById(int id) {
        return projectRepository.findById(id).map(ProjectDTOMapper::toDTO)
                .orElseThrow(() -> new APIRequestException("Project cannot be found by provided id"));
    }

    @Override
    public String createProject(ProjectDTO projectDTO) {
        try {
            projectRepository.save(ProjectDTOMapper.toEntity(projectDTO));
            return "Saved";
        }
        catch (Exception e){
            throw new APIRequestException("Project cannot be saved", e);
        }
    }

    @Override
    public String updateProject(ProjectDTO projectDTO, int id) {
        Project foundProject = projectRepository.findById(id)
                .orElseThrow(() -> new APIRequestException("Project cannot be found by provided id"));

        try {
            foundProject.setName(projectDTO.getName());
            foundProject.setStart_date(projectDTO.getStartDate());
            foundProject.setEnd_date(projectDTO.getEndDate());
            foundProject.setEmployees(projectDTO.getEmployees().stream()
                    .map(ProjectDTOMapper::EmployeeToEntity)
                    .collect(Collectors.toSet()));
            foundProject.setTasks(projectDTO.getTasks().stream()
                    .map(ProjectDTOMapper::TasksToEntity)
                    .collect(Collectors.toSet()));

            projectRepository.save(foundProject);
            return "Updated";
        }
        catch (Exception e){
            throw new APIRequestException("Project cannot be updated", e);
        }
    }

    @Override
    public void deleteProjectById(int id) {
        try {
            projectRepository.deleteById(id);
        }
        catch (Exception e){
            throw new APIRequestException("Project cannot be deleted or found by provided id", e);
        }
    }

}
