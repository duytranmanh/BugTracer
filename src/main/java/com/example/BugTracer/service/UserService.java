package com.example.BugTracer.service;

import com.example.BugTracer.dto.UserDTO;
import jakarta.persistence.EntityNotFoundException;

public interface UserService {
  UserDTO addUser(UserDTO userDTO);
  UserDTO deleteUser(int userId) throws EntityNotFoundException;
  UserDTO updateUser(UserDTO userDTO, int userId) throws EntityNotFoundException;
  UserDTO getUser(int userId) throws EntityNotFoundException;
}
