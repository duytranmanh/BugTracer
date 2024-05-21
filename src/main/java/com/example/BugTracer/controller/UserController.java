package com.example.BugTracer.controller;

import com.example.BugTracer.dto.UserDTO;
import com.example.BugTracer.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping
  public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
    return new ResponseEntity<>(userService.addUser(userDTO), HttpStatus.OK);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") int userId) {
    try {
      return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
    }
    catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
