package com.example.BugTracer.service;

import com.example.BugTracer.dto.ProjectDTO;
import com.example.BugTracer.dto.UserDTO;
import com.example.BugTracer.dto.UserProjectDTO;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

/**
 * Service for project CRUD operation
 */
public interface ProjectService {
  /**
   * insert new user if their username is not used by calling repository
   *
   * @param projectDTO userDTO
   * @return a DTO of the newly created user
   */
  ProjectDTO add(ProjectDTO projectDTO);

  /**
   * delete project by calling repository
   * if id cannot be found, throw EntityNotFoundException
   *
   * @param projectId id of user
   * @return id of the deleted user
   */
  Integer delete(Integer projectId) throws EntityNotFoundException;

  /**
   * update project by calling repository
   * if id cannot be found, throw EntityNotFoundException
   *
   * @param projectDTO id of user
   * @return id of the deleted user
   */
  ProjectDTO update(ProjectDTO projectDTO) throws EntityNotFoundException;

  /**
   * get project by calling repository
   * if id cannot be found, throw EntityNotFoundException
   *
   * @param projectId id of user
   * @return id of the deleted user
   */
  ProjectDTO get(Integer projectId) throws EntityNotFoundException;

  /**
   * get all projects by calling repository
   * @return list of projects
   */
  List<ProjectDTO> getAll();

  void deleteAll();


}
