package com.example.BugTracer.service.impl;

import com.example.BugTracer.dto.UserProjectDTO;
import com.example.BugTracer.model.UserProject;
import com.example.BugTracer.model.UserProjectId;
import com.example.BugTracer.repo.ProjectRepository;
import com.example.BugTracer.repo.UserProjectRepository;
import com.example.BugTracer.repo.UserRepository;
import com.example.BugTracer.service.UserProjectService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  private final TypeMap<UserProjectDTO, UserProject> typeMapToProject;
  public UserProjectServiceImpl(UserProjectRepository userRoleProjectRepository,
      UserRepository userRepository, ProjectRepository projectRepository, ModelMapper modelMapper) {
    this.userProjectRepository = userRoleProjectRepository;
    this.userRepository = userRepository;
    this.projectRepository = projectRepository;
    this.modelMapper = modelMapper;

    typeMapToProject = modelMapper.createTypeMap(UserProjectDTO.class, UserProject.class);

//    Converter<Integer, User> idToUser =
//        c -> userRepository.getReferenceById(c.getSource());
//    Converter<Integer, Project> idToProject =
//        c -> projectRepository.getReferenceById(c.getSource());
//
//    typeMapToId.addMappings(c -> c.using(idToUser).map(UserProjectDTO::getUserId, UserProjectId::setUser));
//    typeMapToId.addMappings(c -> c.using(idToProject).map(UserProjectDTO::getProjectId,
//        UserProjectId::setProject));
//    typeMapToId.addMappings(c -> c.skip(UserProjectDTO::getRole, (dest,v) -> dest.getUser().setRole(v)));

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
      throws EntityExistsException {
    //create a userProjectId
    UserProjectId userProjectId = new UserProjectId();
    userProjectId.setUser(userRepository.getReferenceById(userProjectDTO.getUserId()));
    userProjectId.setProject(projectRepository.getReferenceById(userProjectDTO.getProjectId()));

    //check if the role already existed
    if (!userProjectRepository.existsById(userProjectId)) {

      //map UserProjectDTO to a UserProject object
      UserProject userProject = modelMapper.map(userProjectDTO, UserProject.class);

      userProject.setId(userProjectId);

      //return a dto from a user role that was saved to repo
      return modelMapper.map(userProjectRepository.save(userProject), UserProjectDTO.class);
    } else
      throw new EntityExistsException("Role");
  }

  /**
   * remove user out of a project by calling repository if id cannot be found, throw
   * EntityNotFoundException
   *
   * @param userProjectId id of user and project
   * @return id of the removed user
   */
  @Override
  public Integer delete(UserProjectId userProjectId) throws EntityNotFoundException {
    return null;
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
    return null;
  }

  /**
   * get user's role in the project by calling repository if id cannot be found, throw
   * EntityNotFoundException
   *
   * @param userProjectId id of user and project
   * @return id of the deleted user
   */
  @Override
  public UserProjectDTO get(UserProjectId userProjectId)
      throws EntityNotFoundException {
    return null;
  }

  /**
   * get all projects associated with user by calling repository if id cannot be found, throw
   * EntityNotFoundException
   *
   * @param userId id of user
   * @return id of the deleted user
   */
  @Override
  public List<UserProjectDTO> getUsersProjects(Integer userId) throws EntityNotFoundException {
    return null;
  }

  /**
   * get all projects associated with user by calling repository if id cannot be found, throw
   * EntityNotFoundException
   *
   * @param projectId id of user
   * @return id of the deleted user
   */
  @Override
  public List<UserProjectDTO> getProjectsUsers(Integer projectId) throws EntityNotFoundException {
    return null;
  }

}
