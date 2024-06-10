package com.example.BugTracer.controller;

import com.example.BugTracer.dto.UserDTO;
import com.example.BugTracer.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for user endpoint
 */
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  /**
   * UserService DI
   * @param userService
   */
  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Controller for adding user
   * @param userDTO userDTO
   * @return ResponseEntity with userDTO and http status
   */
  @PostMapping
  public ResponseEntity<UserDTO> add(@Valid @RequestBody UserDTO userDTO) {
      return ResponseEntity.ok(userService.add(userDTO));
  }

  /**
   * Controller for deleting user
   * @param userId id of user
   * @return ResponseEntity with userDTO and http status
   */
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Integer> delete(@NotNull @PathVariable("id") Integer userId) {
      return ResponseEntity.ok(userService.delete(userId));
  }

  /**
   * Controller for retrieving user
   * @param userId id of user
   * @return ResponseEntity with userDTO and http status
   */
  @GetMapping(value = "/{id}")
  public ResponseEntity<UserDTO> get(@NotNull @PathVariable("id") Integer userId) {
      return ResponseEntity.ok(userService.get(userId));
  }

  /**
   * Controller for updating user
   * @param userDTO userDTO
   * @return ResponseEntity with userDTO and http status
   */
  @PutMapping
  public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO userDTO) {
      return ResponseEntity.ok(userService.update(userDTO));
  }

  @GetMapping
  public ResponseEntity<List<UserDTO>> getAll() {
    return ResponseEntity.ok(userService.getAll());
  }
}
