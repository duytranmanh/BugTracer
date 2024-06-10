package com.example.BugTracer.service.impl;

import com.example.BugTracer.dto.ProjectDTO;
import com.example.BugTracer.dto.UserDTO;
import com.example.BugTracer.model.Project;
import com.example.BugTracer.repo.ProjectRepository;
import com.example.BugTracer.service.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
  private final ProjectRepository projectRepository;
  private final ModelMapper modelMapper;
  private final TypeMap typeMapToProject;

  /**
   * Dependency injection set up
   * Creating mapping rules when mapping from userDTO to user and in reverse
   *
   * @param projectRepository repository for user
   * @param modelMapper    model mapper
   */
  public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper) {
    this.projectRepository = projectRepository;
    this.modelMapper = modelMapper;

    typeMapToProject = modelMapper.createTypeMap(ProjectDTO.class, Project.class);
  }

  /**
   * insert new user if their username is not used by calling repository
   *
   * @param projectDTO userDTO
   * @return a DTO of the newly created user
   */
  @Override
  public ProjectDTO add(ProjectDTO projectDTO) {
    Project project = modelMapper.map(projectDTO, Project.class);
    Project projectSaved = projectRepository.save(project);
    ProjectDTO returnProject = modelMapper.map(projectSaved, ProjectDTO.class);
    return returnProject;
  }

  /**
   * delete project by calling repository
   * if id cannot be found, throw EntityNotFoundException
   *
   * @param projectId id of user
   * @return id of the deleted user
   */
  @Override
  public Integer delete(Integer projectId) throws EntityNotFoundException {
    if (projectRepository.existsById(projectId)) {
      projectRepository.deleteById(projectId);
      return projectId;
    } else
      throw new EntityNotFoundException("project");
  }

  /**
   * update project by calling repository
   * if id cannot be found, throw EntityNotFoundException
   *
   * @param projectDTO id of user
   * @return id of the deleted user
   */
  @Override
  public ProjectDTO update(ProjectDTO projectDTO) throws EntityNotFoundException {
    if (projectRepository.existsById(projectDTO.getId())) {
      Provider<Project> project = p -> projectRepository.getReferenceById(projectDTO.getId());
      typeMapToProject.setProvider(project);

      return modelMapper.map(projectRepository.save(modelMapper.map(projectDTO, Project.class)), ProjectDTO.class);
    } else
      throw new EntityNotFoundException("project");
  }

  /**
   * get project by calling repository
   * if id cannot be found, throw EntityNotFoundException
   *
   * @param projectId id of user
   * @return id of the deleted user
   */
  @Override
  public ProjectDTO get(Integer projectId) throws EntityNotFoundException {
    if (projectRepository.existsById(projectId))
      return modelMapper.map(projectRepository.getReferenceById(projectId), ProjectDTO.class);
    else throw new EntityNotFoundException("project");
  }

  /**
   * get all projects by calling repository
   *
   * @return list of projects
   */
  @Override
  public List<ProjectDTO> getAll() {
    List<ProjectDTO> dtoList = new ArrayList<>();
    for (Project project : projectRepository.findAll()) {
      dtoList.add(modelMapper.map(project, ProjectDTO.class));
    }

    return dtoList;
  }

  @Override
  public void deleteAll() {
    projectRepository.deleteAll();
  }
}
