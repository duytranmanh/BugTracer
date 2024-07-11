package com.example.BugTracer.controller;

import com.example.BugTracer.dto.TaskDTO;
import com.example.BugTracer.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * private helper method to bind project id to dto
     * @param taskDTO
     * @param projectId
     * @return
     */
    private TaskDTO bindId(TaskDTO taskDTO, Integer projectId, Integer taskId) {
        if (projectId != null) taskDTO.setProjectId(projectId);
        if (taskId != null) taskDTO.setId(taskId);
        return taskDTO;
    }

    @PostMapping("projects/{projectId}/tasks")
    public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO taskDTO, @PathVariable("projectId") Integer projectId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.add(bindId(taskDTO, projectId, null)));
    }

    @PutMapping("projects/{projectId}/tasks/{taskId}")
    public ResponseEntity<TaskDTO> update(@RequestBody TaskDTO taskDTO, @PathVariable("projectId") Integer projectId, @PathVariable("taskId") Integer taskId) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.update(bindId(taskDTO, projectId, taskId)));
    }

    @GetMapping("tasks/{taskId}")
    public ResponseEntity<TaskDTO> get(@PathVariable("taskId") Integer taskId) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.get(taskId));
    }

    @GetMapping("projects/{projectId}/tasks")
    public ResponseEntity<List<TaskDTO>> getAllFromProject(@PathVariable Integer projectId) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllProjectTasks(projectId));
    }

    @DeleteMapping("tasks/{taskId}")
    public ResponseEntity<Integer> delete(@PathVariable("taskId") Integer taskId) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.delete(taskId));
    }
}
