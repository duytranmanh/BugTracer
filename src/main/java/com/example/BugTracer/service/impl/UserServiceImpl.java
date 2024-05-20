package com.example.BugTracer.service.impl;

import com.example.BugTracer.dto.UserDTO;
import com.example.BugTracer.domain.User;
import com.example.BugTracer.repo.UserRepository;
import com.example.BugTracer.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {


  private final UserRepository userRepository;
  private final ModelMapper modelMapper;
  private final TypeMap<User, UserDTO> typeMap;

  public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
    this.userRepository = userRepository;
    this.modelMapper = modelMapper;
    typeMap = modelMapper.createTypeMap(User.class, UserDTO.class);
    typeMap.addMappings(ModelMapper -> ModelMapper.skip(UserDTO::setPassword));
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

      //return a dto from a user that was saved to repo
      return modelMapper.map(userRepository.save(user), UserDTO.class);
    }
    return modelMapper.map(userFound, UserDTO.class);
  }

  @Override
  public UserDTO deleteUser(int userId) {
    return null;
  }

  @Override
  public UserDTO updateUser(UserDTO userDTO) {
    return null;
  }

  @Override
  public UserDTO getUser(int userId) {
    return null;
  }
}