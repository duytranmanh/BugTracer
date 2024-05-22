package com.example.BugTracer.service.impl;

import com.example.BugTracer.dto.ProjectDTO;
import com.example.BugTracer.service.ProjectService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

  @Override
  public ProjectDTO addProject(ProjectDTO projectDTO) {
    return null;
  }

  @Override
  public ProjectDTO deleteProject(int projectId) throws EntityNotFoundException {
    return null;
  }

  @Override
  public ProjectDTO updateProject(ProjectDTO projectDTO, int projectId)
      throws EntityNotFoundException, EntityExistsException {
    return null;
  }

  @Override
  public ProjectDTO getProject(int projectId) throws EntityNotFoundException {
    return null;
  }
}
