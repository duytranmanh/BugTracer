package com.example.BugTracer.service;

import com.example.BugTracer.dto.ProjectDTO;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

public interface ProjectService {
  ProjectDTO addProject(ProjectDTO projectDTO) throws EntityExistsException;
  ProjectDTO deleteProject(int projectId) throws EntityNotFoundException;
  ProjectDTO updateProject(ProjectDTO projectDTO, int projectId) throws EntityNotFoundException,
      EntityExistsException;
  ProjectDTO getProject(int projectId) throws EntityNotFoundException;
}
