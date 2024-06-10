package com.example.BugTracer.service;

import com.example.BugTracer.dto.ProjectDTO;
import com.example.BugTracer.dto.UserDTO;
import com.example.BugTracer.dto.UserProjectDTO;
import com.example.BugTracer.model.UserProjectId;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface UserProjectService {
  /**
   * insert new user to project if he/she is not in the project by calling repository
   * otherwise throw EntityExistException
   *
   * @param userProjectDTO userRoleProjectDTO
   * @return URPdto
   */
  UserProjectDTO add(UserProjectDTO userProjectDTO) throws EntityExistsException,EntityNotFoundException;

  /**
   * remove user out of a project by calling repository if id cannot be found, throw
   * EntityNotFoundException
   *
   * @param userProjectId id of user and project
   * @return id of the removed user
   */
  void delete(Integer userId, Integer projectId) throws EntityNotFoundException;

  /**
   * update user role in project by calling repository
   * if id cannot be found, throw EntityNotFoundException
   *
   * @param userProjectDTO id of user and projeect
   * @return id of the deleted user
   */
  UserProjectDTO update(UserProjectDTO userProjectDTO) throws EntityNotFoundException;

  /**
   * get user's role in the project by calling repository if id cannot be found, throw
   * EntityNotFoundException
   *
   * @param userId    id of user and project
   * @param projectId
   * @return id of the deleted user
   */
  UserProjectDTO get(Integer userId, Integer projectId) throws EntityNotFoundException;

  /**
   * get all projects associated with user by calling repository if id cannot be found, throw
   * EntityNotFoundException
   *
   * @param userId id of user
   * @return
   */
  List<ProjectDTO> getUsersProjects(Integer userId) throws EntityNotFoundException;

  /**
   * get all projects associated with user by calling repository if id cannot be found, throw
   * EntityNotFoundException
   *
   * @param projectId id of user
   * @return list of
   */
  List<UserDTO> getProjectsUsers(Integer projectId) throws EntityNotFoundException;
}
