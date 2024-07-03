package com.example.BugTracer.service.impl;

import com.example.BugTracer.dto.ProjectDTO;
import com.example.BugTracer.dto.UserDTO;
import com.example.BugTracer.dto.UserProjectDTO;
import com.example.BugTracer.model.Project;
import com.example.BugTracer.model.User;
import com.example.BugTracer.model.UserProject;
import com.example.BugTracer.model.UserProjectId;
import com.example.BugTracer.repo.ProjectRepository;
import com.example.BugTracer.repo.UserProjectRepository;
import com.example.BugTracer.repo.UserRepository;
import com.example.BugTracer.service.UserProjectService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of UserRoleProjectService
 */
@Service
@Transactional
public class UserProjectServiceImpl implements UserProjectService {

  private final UserProjectRepository userProjectRepository;
  private final UserRepository userRepository;
  private final ProjectRepository projectRepository;
  private final ModelMapper modelMapper;
  private final TypeMap<UserProjectDTO, UserProject> typeMapToObject;
  public UserProjectServiceImpl(UserProjectRepository userRoleProjectRepository,
      UserRepository userRepository, ProjectRepository projectRepository, ModelMapper modelMapper) {
    this.userProjectRepository = userRoleProjectRepository;
    this.userRepository = userRepository;
    this.projectRepository = projectRepository;
    this.modelMapper = modelMapper;

    typeMapToObject = modelMapper.createTypeMap(UserProjectDTO.class, UserProject.class);
    typeMapToObject.addMappings(
        src -> src.when(Conditions.isNotNull()).map(UserProjectDTO::getRole, UserProject::setRole));
  }

  /**
   * insert relation user to project if he/she is not in the project by calling repository otherwise
   * throw EntityExistException
   *
   * @param userProjectDTO userRoleProjectDTO
   * @return URPdto
   */
  @Override
  public UserProjectDTO add(UserProjectDTO userProjectDTO)
      throws EntityExistsException, EntityNotFoundException {
    UserProjectId userProjectId = validateAndGetUserProjectId(userProjectDTO.getUserId(),
        userProjectDTO.getProjectId());

    //check if the role already existed
    if (!userProjectRepository.existsById(userProjectId)) {

      //map UserProjectDTO to a UserProject object
      UserProject userProject = modelMapper.map(userProjectDTO, UserProject.class);

      userProject.setId(userProjectId);

      //return a dto from a user role that was saved to repo
      return modelMapper.map(userProjectRepository.saveAndFlush(userProject), UserProjectDTO.class);
    } else
      throw new EntityExistsException("Role");
  }

  /**
   * remove user out of a project by calling repository if id cannot be found, throw
   * EntityNotFoundException
   *
   * @param userId id of user
   * @param projectId id of project
   * @return id of the removed user
   */
  @Override
  public void delete(Integer userId, Integer projectId) throws EntityNotFoundException {
    UserProjectId userProjectId = validateAndGetUserProjectId(userId, projectId);

    if (userProjectRepository.existsById(userProjectId)) {
      userProjectRepository.deleteById(userProjectId);
    }
    else throw new EntityNotFoundException("user role in project");
  }

  /**
   * update user role in project by calling repository if id cannot be found, throw
   * EntityNotFoundException
   *
   * @param userProjectDTO id of user and projeect
   * @return id of the deleted user
   */
  @Override
  public UserProjectDTO update(UserProjectDTO userProjectDTO)
      throws EntityNotFoundException {
    UserProjectId userProjectId = validateAndGetUserProjectId(userProjectDTO.getUserId(),
        userProjectDTO.getProjectId());

    if (userProjectRepository.existsById(userProjectId)) {
      //search for existed project that user want to modify
      UserProject existingUserProject = userProjectRepository.getReferenceById(userProjectId);

      //set provider for mapping
      Provider<UserProject> userProjectProvider = p -> existingUserProject;
      typeMapToObject.setProvider(userProjectProvider);

      if (userProjectDTO.getRole().equals(existingUserProject.getRole())) throw new EntityExistsException("user role");

      //map dto to object
      //then map object to dto for return statement
      return modelMapper.map(userProjectRepository.saveAndFlush(modelMapper.map(userProjectDTO,
          UserProject.class)), UserProjectDTO.class);
    }

    return null;
  }

  /**
   * get user's role in the project by calling repository if id cannot be found, throw
   * EntityNotFoundException
   *
   * @param userId    id of user and project
   * @param projectId
   * @return id of the deleted user
   */
  @Override
  public UserProjectDTO get(Integer userId, Integer projectId)
      throws EntityNotFoundException {
    //get UserProjectId
    UserProjectId userProjectId = validateAndGetUserProjectId(userId, projectId);

    if (userProjectRepository.existsById(userProjectId)) {
      return modelMapper.map(userProjectRepository.getReferenceById(userProjectId), UserProjectDTO.class);
    }
    else throw new EntityNotFoundException("user role in project");
  }

  /**
   * get all projects associated with user by calling repository if id cannot be found, throw
   * EntityNotFoundException
   *
   * @param userId id of user
   * @return
   */
  @Override
  public List<ProjectDTO> getUsersProjects(Integer userId) throws EntityNotFoundException {
    //if user is not found throw exception
    if (!userRepository.existsById(userId)) throw new EntityNotFoundException("user");
    //retrieve user
    User user = userRepository.getReferenceById(userId);

    //get userProject list from retrieved user
    List<UserProject> userProjectList = user.getUserProjectList();
    List<ProjectDTO> projectDTOList = new ArrayList<>();

    for (UserProject userProject: userProjectList) {
      projectDTOList.add(modelMapper.map(userProject.getId().getProject(), ProjectDTO.class));
    }

    return projectDTOList;
  }

  /**
   * get all projects associated with user by calling repository if id cannot be found, throw
   * EntityNotFoundException
   *
   * @param projectId id of user
   * @return id of the deleted user
   */
  @Override
  public List<UserDTO> getProjectsUsers(Integer projectId) throws EntityNotFoundException {
    //if user is not found throw exception
    if (!projectRepository.existsById(projectId)) throw new EntityNotFoundException("project");
    //retrieve user
    Project project = projectRepository.getReferenceById(projectId);

    //get userProject list from retrieved user
    List<UserProject> userProjectList = project.getUserProjectList();
    List<UserDTO> userDTOList = new ArrayList<>();

    for (UserProject userProject: userProjectList) {
      userDTOList.add(modelMapper.map(userProject.getId().getUser(), UserDTO.class));
    }

    return userDTOList;
  }

  /**
   * helper class to validate and create UserProjectId
   * @param userId
   * @param projectId
   * @return
   * @throws EntityNotFoundException
   */
  private UserProjectId validateAndGetUserProjectId(Integer userId, Integer projectId) throws EntityNotFoundException {
    //if userId is not valid throw exception
    if (!userRepository.existsById(userId)) {
      throw new EntityNotFoundException("User not found");
    }
    //if project id is not valid throw exception
    if (!projectRepository.existsById(projectId)) {
      throw new EntityNotFoundException("Project not found");
    }

    return new UserProjectId(userRepository.getReferenceById(userId),
        projectRepository.getReferenceById(projectId));
  }


}
