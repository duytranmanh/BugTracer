package com.example.BugTracer.service;

import com.example.BugTracer.dto.UserDTO;

public interface UserService {
  UserDTO addUser(UserDTO userDTO);
  UserDTO deleteUser(int userId);
  UserDTO updateUser(UserDTO userDTO);
  UserDTO getUser(int userId);
}
