package com.example.BugTracer.controller;

import com.example.BugTracer.dto.ProjectDTO;
import com.example.BugTracer.dto.UserDTO;
import com.example.BugTracer.dto.UserProjectDTO;
import com.example.BugTracer.service.UserProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserProjectController {

  private final UserProjectService userProjectService;

  public UserProjectController(UserProjectService userProjectService) {
    this.userProjectService = userProjectService;
  }

  /**
   * assign a user with a role in the project
   * @param userProjectDTO
   * @return
   */
  @PostMapping("/projects/{projectId}/roles")
  public ResponseEntity<UserProjectDTO> add(@PathVariable("projectId") Integer projectId,
      @RequestBody UserProjectDTO userProjectDTO) {
    userProjectDTO.setProjectId(projectId);
    return ResponseEntity.ok(userProjectService.add(userProjectDTO));
  }

  @DeleteMapping("/projects/{projectId}/roles")
  public ResponseEntity<Void> delete(@PathVariable("projectId") Integer projectId,
      @RequestBody UserProjectDTO userProjectDTO) {
    userProjectDTO.setProjectId(projectId);
    userProjectService.delete(userProjectDTO.getUserId(), userProjectDTO.getProjectId());
    return ResponseEntity.ok().build();
  }

  @GetMapping("/projects/{projectId}/roles")
  public ResponseEntity<UserProjectDTO> get(@PathVariable("projectId") Integer projectId,
      @RequestBody UserProjectDTO userProjectDTO) {
    userProjectDTO.setProjectId(projectId);
    return ResponseEntity.ok(userProjectService.get(userProjectDTO.getUserId(),
        userProjectDTO.getProjectId()));
  }

  @PutMapping("/projects/{projectId}/roles")
  public ResponseEntity<UserProjectDTO> update(@PathVariable("projectId") Integer projectId,
      @RequestBody UserProjectDTO userProjectDTO) {
    userProjectDTO.setProjectId(projectId);
    return ResponseEntity.ok(userProjectService.update(userProjectDTO));
  }

  @GetMapping("/users/{userId}/projects")
  public ResponseEntity<List<ProjectDTO>> getAllProjectsFromAUser(@PathVariable("userId") Integer userId) {
    return ResponseEntity.ok(userProjectService.getUsersProjects(userId));
  }

  @GetMapping("/projects/{projectId}/users")
  public ResponseEntity<List<UserDTO>> getAllUsersFromAProject(@PathVariable("projectId") Integer projectId) {
    return ResponseEntity.ok(userProjectService.getProjectsUsers(projectId));
  }
}
