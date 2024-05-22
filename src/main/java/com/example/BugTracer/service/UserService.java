package com.example.BugTracer.service;

import com.example.BugTracer.dto.UserDTO;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

public interface UserService {
  UserDTO add(UserDTO userDTO) throws EntityExistsException;
  Integer delete(Integer userId) throws EntityNotFoundException;
  UserDTO update(UserDTO userDTO) throws EntityNotFoundException, EntityExistsException;
  UserDTO get(Integer userId) throws EntityNotFoundException;
}
