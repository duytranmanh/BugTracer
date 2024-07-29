package com.example.BugTracer.controller;

import com.example.BugTracer.dto.CommentDTO;
import com.example.BugTracer.service.CommentService;
import jakarta.persistence.Id;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/tasks/{taskId}/comments")
    public ResponseEntity<CommentDTO> add(@RequestBody CommentDTO commentDTO, @PathVariable Integer taskId) {
        return ResponseEntity.ok(commentService.add(bindId(commentDTO, taskId, null)));
    }

    @PutMapping("/tasks/{taskId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> update(@RequestBody CommentDTO commentDTO, @PathVariable Integer commentId, @PathVariable Integer taskId) {
        return ResponseEntity.ok(commentService.update(bindId(commentDTO, taskId, commentId)));
    }

    @GetMapping("comments/{commentId}")
    public ResponseEntity<CommentDTO> get(@PathVariable Integer commentId) {
        return ResponseEntity.ok(commentService.get(commentId));
    }

    @DeleteMapping("comments/{commentId}")
    public ResponseEntity<Integer> delete(@PathVariable Integer commentId) {
        return ResponseEntity.ok(commentService.delete(commentId));
    }

    @GetMapping("/tasks/{taskId}/comments")
    public ResponseEntity<List<CommentDTO>> getAllCommentsFromTask(@PathVariable Integer taskId) {
        return ResponseEntity.ok(commentService.getAllCommentInTask(taskId));
    }

    /**
     * private helper method that binds commentDTO with taskId
     * @param commentDTO
     * @param taskId
     * @return
     */
    private CommentDTO bindId(CommentDTO commentDTO, Integer taskId, Integer commentId) {
        commentDTO.setTaskId(taskId);
        commentDTO.setId(commentId);
        return commentDTO;
    }
}
