package com.example.BugTracer.service;

import com.example.BugTracer.dto.UserDTO;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public interface UserService {
  UserDTO add(UserDTO userDTO) throws EntityExistsException;
  Integer delete(Integer userId) throws EntityNotFoundException;
  UserDTO update(UserDTO userDTO) throws EntityNotFoundException, EntityExistsException;
  UserDTO get(Integer userId) throws EntityNotFoundException;

  //TODO:get all user
  List<UserDTO> getAll() throws EmptyResultDataAccessException;

  UserDTO getByUsername(String username) throws EntityNotFoundException;
}
