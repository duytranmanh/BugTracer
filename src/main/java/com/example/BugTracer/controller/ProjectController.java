package com.example.BugTracer.controller;

import com.example.BugTracer.dto.ProjectDTO;
import com.example.BugTracer.service.ProjectService;
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
 * Controller for project endpoint
 */
@RestController
@RequestMapping(value = "/projects")
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
  public ResponseEntity<ProjectDTO> addProject(@Valid @RequestBody ProjectDTO projectDTO) {
    return ResponseEntity.ok(projectService.add(projectDTO));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Integer> deleteProject(@NotNull @PathVariable("id") Integer projectId) {
    return ResponseEntity.ok(projectService.delete(projectId));
  }

  @PutMapping
  public ResponseEntity<ProjectDTO> updateProject(@Valid @RequestBody ProjectDTO projectDTO) {
    return ResponseEntity.ok(projectService.update(projectDTO));
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ProjectDTO> getProject(@NotNull @PathVariable("id") Integer projectId) {
    return ResponseEntity.ok(projectService.get(projectId));
  }

  @GetMapping
  public ResponseEntity<List<ProjectDTO>> getAllProjects() {
    return ResponseEntity.ok(projectService.getAll());
  }
}
