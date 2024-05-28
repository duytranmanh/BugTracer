package com.example.BugTracer.service.impl;

import com.example.BugTracer.dto.UserDTO;
import com.example.BugTracer.model.User;
import com.example.BugTracer.repo.UserRepository;
import com.example.BugTracer.service.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.modelmapper.Conditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service for User CRUD operation
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final ModelMapper modelMapper;
  private final TypeMap<User, UserDTO> typeMapToDTO;
  private final TypeMap<UserDTO, User> typeMapToUser;

  /**
   * Dependency injection set up Creating mapping rules when mapping from userDTO to user and in
   * reverse
   *
   * @param userRepository repository for user
   * @param modelMapper    model mapper
   */
  public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
    this.userRepository = userRepository;
    this.modelMapper = modelMapper;

    //property mapping: skip password when mapping from user to userDTO
    typeMapToDTO = modelMapper.createTypeMap(User.class, UserDTO.class);
    typeMapToDTO.addMappings(src -> src.skip(UserDTO::setPassword));

    //property mapping: skip email, password or username when mapping from userDTO to user when null
    typeMapToUser = modelMapper.createTypeMap(UserDTO.class, User.class);
    typeMapToUser.addMappings(
        src -> src.when(Conditions.isNotNull()).map(UserDTO::getEmail, User::setEmail));
    typeMapToUser.addMappings(
        src -> src.when(Conditions.isNotNull()).map(UserDTO::getPassword, User::setPassword));
    typeMapToUser.addMappings(
        src -> src.when(Conditions.isNotNull()).map(UserDTO::getUsername, User::setUsername));
  }


  /**
   * this method call repository to insert new user if their username is not used
   *
   * @param userDTO userDTO
   * @return a DTO of the newly created user
   */
  @Override
  public UserDTO add(UserDTO userDTO) {
    //check if username is used
    if (!userRepository.existsByUsername(userDTO.getUsername())) {
      //map dto to a user
      User user = modelMapper.map(userDTO, User.class);

      //return a dto from a user that was saved to repo
      return modelMapper.map(userRepository.save(user), UserDTO.class);
    } else
      throw new EntityExistsException("username");
  }


  /**
   * this method call repository to delete new user if id cannot be found, throw
   * EntityNotFoundException
   *
   * @param userId id of user
   * @return id of the deleted user
   */
  @Override
  public Integer delete(Integer userId) throws EntityNotFoundException {

    if (userRepository.existsById(userId)) {
      userRepository.deleteById(userId);
      return userId;
    } else {
      throw new EntityNotFoundException("user");
    }
  }

  /**
   * this method call repository to update new user if their id is valid if new user is used, throw
   * a entity exists exception if no user associated with the id, throw entity not found exception
   *
   * @param userDTO userDTO
   * @return a DTO of the newly updated user
   */
  @Override
  public UserDTO update(UserDTO userDTO) throws EntityNotFoundException, EntityExistsException {

    if (userRepository.existsById(userDTO.getId())) {
      User user = userRepository.getReferenceById(userDTO.getId());
      Provider<User> userProvider = p -> user;
      typeMapToUser.setProvider(userProvider);

      if (!userDTO.getUsername().equals(user.getUsername()) && userRepository.existsByUsername(
          userDTO.getUsername()))
        throw new EntityExistsException("username");

      return modelMapper.map(userRepository.save(modelMapper.map(userDTO, User.class)),
          UserDTO.class);
    } else
      throw new EntityNotFoundException("user");

  }

  /**
   * this method call repository to retrieve new user if their id is valid if no user associated
   * with the id, throw entity not found exception
   *
   * @param userId id of user
   * @return a DTO of the user
   */
  @Override
  public UserDTO get(Integer userId) throws EntityNotFoundException {
    if (userRepository.existsById(userId))
      return modelMapper.map(userRepository.getReferenceById(userId), UserDTO.class);
    else
      throw new EntityNotFoundException("user");
  }
}
