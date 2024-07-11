package com.example.BugTracer.service.impl;

import com.example.BugTracer.dto.ProjectDTO;
import com.example.BugTracer.model.Project;
import com.example.BugTracer.repo.ProjectRepository;
import com.example.BugTracer.service.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
  private final ProjectRepository projectRepository;
  private final ModelMapper modelMapper;
  private final TypeMap<ProjectDTO, Project> typeMapToProject;
  private final TypeMap<Project, ProjectDTO> typeMapToDTO;

  /**
   * Constructor for dependency injection and mapping setup.
   *
   * @param projectRepository the project repository
   * @param modelMapper       the model mapper
   */
  public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper) {
    this.projectRepository = projectRepository;
    this.modelMapper = modelMapper;

    typeMapToProject = modelMapper.createTypeMap(ProjectDTO.class, Project.class);
    typeMapToDTO = modelMapper.createTypeMap(Project.class, ProjectDTO.class);

    typeMapToProject.implicitMappings();
    typeMapToDTO.implicitMappings();
  }

  /**
   * Add a new project.
   *
   * @param projectDTO the project DTO
   * @return the newly created project DTO
   */
  @Override
  public ProjectDTO add(ProjectDTO projectDTO) {
    Project project = modelMapper.map(projectDTO, Project.class);
    project.setId(null); // Ensure the ID is null to let the database generate it
    Project savedProject = projectRepository.saveAndFlush(project);
    return modelMapper.map(savedProject, ProjectDTO.class);
  }

  /**
   * Delete a project by its ID.
   *
   * @param projectId the ID of the project
   * @return the ID of the deleted project
   * @throws EntityNotFoundException if the project is not found
   */
  @Override
  public Integer delete(Integer projectId) throws EntityNotFoundException {
    if (projectRepository.existsById(projectId)) {
      projectRepository.deleteById(projectId);
      return projectId;
    } else {
      throw new EntityNotFoundException("Project not found with ID: " + projectId);
    }
  }

  /**
   * Update a project.
   *
   * @param projectDTO the project DTO
   * @return the updated project DTO
   * @throws EntityNotFoundException if the project is not found
   */
  @Override
  public ProjectDTO update(ProjectDTO projectDTO) throws EntityNotFoundException {
    if (projectRepository.existsById(projectDTO.getId())) {
      Provider<Project> projectProvider = p -> projectRepository.getReferenceById(projectDTO.getId());
      typeMapToProject.setProvider(projectProvider);

      Project updatedProject = modelMapper.map(projectDTO, Project.class);
      Project savedProject = projectRepository.saveAndFlush(updatedProject);
      return modelMapper.map(savedProject, ProjectDTO.class);
    } else {
      throw new EntityNotFoundException("Project not found with ID: " + projectDTO.getId());
    }
  }

  /**
   * Get a project by its ID.
   *
   * @param projectId the ID of the project
   * @return the project DTO
   * @throws EntityNotFoundException if the project is not found
   */
  @Override
  public ProjectDTO get(Integer projectId) throws EntityNotFoundException {
    return projectRepository.findById(projectId)
            .map(project -> modelMapper.map(project, ProjectDTO.class))
            .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + projectId));
  }

  /**
   * Get all projects.
   *
   * @return the list of project DTOs
   */
  @Override
  public List<ProjectDTO> getAll() {
    return projectRepository.findAll().stream()
            .map(project -> modelMapper.map(project, ProjectDTO.class))
            .collect(Collectors.toList());
  }

  /**
   * Delete all projects.
   */
  @Override
  public void deleteAll() {
    projectRepository.deleteAll();
  }
}
