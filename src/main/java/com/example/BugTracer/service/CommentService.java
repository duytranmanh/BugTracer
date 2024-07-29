package com.example.BugTracer.service;

import com.example.BugTracer.dto.CommentDTO;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface CommentService {
    /**
     * Add a new comment in a task
     * @param commentDTO all information about the comment
     * @return CommentDTO containing id and information
     * @throws EntityExistsException if CommentDto is passed in with an invalid id
     */
    CommentDTO add(CommentDTO commentDTO) throws EntityExistsException;

    /**
     * update an existing comment in a task
     * @param commentDTO containing appropriate data for update
     * @return a dto of latest update
     * @throws EntityNotFoundException if no task is associated with id
     */
    CommentDTO update(CommentDTO commentDTO) throws EntityNotFoundException;

    /**
     * delete an existing comment in a task
     * @param id comment id
     * @return id of the deleted comment
     * @throws EntityNotFoundException if no comment is associated with id
     */
    Integer delete(Integer id) throws EntityNotFoundException;

    /**
     * get a comment
     * @param id comment id
     * @return dto of the requested task
     * @throws EntityNotFoundException if no comment is associated with id
     */
    CommentDTO get(Integer id) throws EntityNotFoundException;

    /**
     * get all comment in a task
     * @return list of all task
     */
    List<CommentDTO> getAllCommentInTask(Integer taskId) throws EntityNotFoundException;
}
