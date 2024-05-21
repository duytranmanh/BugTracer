package com.example.BugTracer.service.impl;

import com.example.BugTracer.dto.UserDTO;
import com.example.BugTracer.domain.User;
import com.example.BugTracer.repo.UserRepository;
import com.example.BugTracer.service.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;
import org.modelmapper.Conditions;
import java.util.Calendar;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {


  private final UserRepository userRepository;
  private final ModelMapper modelMapper;
  private final TypeMap<User, UserDTO> typeMapToDTO;

  private final TypeMap<UserDTO, User> typeMapToUser;

  public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
    this.userRepository = userRepository;
    this.modelMapper = modelMapper;

    //property mapping: skip password when mapping from user to userDTO
    typeMapToDTO = modelMapper.createTypeMap(User.class, UserDTO.class);
    typeMapToDTO.addMappings(ModelMapper -> ModelMapper.skip(UserDTO::setPassword));


    typeMapToUser = modelMapper.createTypeMap(UserDTO.class, User.class);
    typeMapToUser.addMappings(src -> src.skip(User::setId));
    typeMapToUser.addMappings(src -> src.when(Conditions.isNotNull()).map(UserDTO::getEmail,
    User::setEmail));
    typeMapToUser.addMappings(ModelMapper -> ModelMapper.when(Conditions.isNotNull()).map(UserDTO::getPassword,
    User::setPassword));
    typeMapToUser.addMappings(src -> src.when(Conditions.isNotNull()).map(UserDTO::getUsername,
    User::setUsername));
  }


  @Override
  public UserDTO addUser(UserDTO userDTO) {
    User userFound = userRepository.findByUsername(userDTO.username);

    if (userFound == null) {
      //map dto to a user
      User user = new User();
      user.setUsername(userDTO.username);
      user.setPassword(userDTO.password);
      user.setEmail(userDTO.email);

      //set last updated
      user.setLastUpdated(Calendar.getInstance().getTime());
//      User user = modelMapper.map(userDTO, User.class);

      //return a dto from a user that was saved to repo
      return modelMapper.map(userRepository.save(user), UserDTO.class);
    }
    return modelMapper.map(userFound, UserDTO.class);
  }

  @Override
  public UserDTO deleteUser(int userId) throws EntityNotFoundException {
    Optional<User> userFound = userRepository.findById(userId);

    if (userFound.isPresent()) {
      userRepository.deleteById(userId);
      return modelMapper.map(userFound, UserDTO.class);
    }
    else {
      throw new EntityNotFoundException("Cannot find user!");
    }
  }

  @Override
  public UserDTO updateUser(UserDTO userDTO, int userId) throws EntityNotFoundException {
    Optional<User> userFound = userRepository.findById(userId);

    if (userFound.isPresent()) {
      if (userRepository.findByUsername(userDTO.username) != null) throw new EntityExistsException("this username is taken");

      Provider<User> userProvider = p -> userRepository.getById(userId);
      typeMapToUser.setProvider(userProvider);

      User user = modelMapper.map(userDTO, User.class);

      //set last updated
      user.setLastUpdated(Calendar.getInstance().getTime());

      return modelMapper.map(userRepository.save(user), UserDTO.class);
    }
    else throw new EntityNotFoundException();

  }

  @Override
  public UserDTO getUser(int userId) throws EntityNotFoundException {
    Optional<User> userFound = userRepository.findById(userId);
    if (userFound.isPresent()) return modelMapper.map(userFound, UserDTO.class);
    else throw new EntityNotFoundException();
  }
}
