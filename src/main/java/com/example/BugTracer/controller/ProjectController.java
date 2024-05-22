package com.example.BugTracer.controller;

import com.example.BugTracer.dto.ProjectDTO;
import com.example.BugTracer.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {
  private final ProjectService projectService;

  public ProjectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  /**
   * Controller for adding Project
   *
   * @param projectDTO contains id and necessary info
   * @return ProjectDTO and Http Status(200-ok, 404- not found)
   */
  @PostMapping
  public ResponseEntity<ProjectDTO> addProject(@RequestBody ProjectDTO projectDTO) {

    return ResponseEntity.ok(projectService.addProject(projectDTO));
  }

}
