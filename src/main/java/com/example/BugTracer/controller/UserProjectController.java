package com.example.BugTracer.controller;

import com.example.BugTracer.dto.UserProjectDTO;
import com.example.BugTracer.service.UserProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
  @PostMapping("/project/{projectId}/role")
  public ResponseEntity<UserProjectDTO> add(@RequestBody UserProjectDTO userProjectDTO) {
    return ResponseEntity.ok(userProjectService.add(userProjectDTO));
  }

}
