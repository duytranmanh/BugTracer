package com.example.BugTracer.service;

import com.example.BugTracer.dto.ProjectDTO;
import com.example.BugTracer.dto.UserDTO;
import com.example.BugTracer.dto.UserProjectDTO;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public interface UserService {
  UserDTO add(UserDTO userDTO) throws EntityExistsException;
  Integer delete(Integer userId) throws EntityNotFoundException;
  UserDTO update(UserDTO userDTO) throws EntityNotFoundException, EntityExistsException;
  UserDTO get(Integer userId) throws EntityNotFoundException;

  List<UserDTO> getAll() throws EmptyResultDataAccessException;

  UserDTO getByUsername(String username) throws EntityNotFoundException;
  void deleteAll();
}
