package com.example.BugTracer.controller;

import com.example.BugTracer.dto.UserDTO;
import com.example.BugTracer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
  public UserDTO addUser(@RequestBody UserDTO userDTO) {
    return userService.addUser(userDTO);
  }
}
