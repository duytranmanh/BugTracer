package com.example.BugTracer.service;

import com.example.BugTracer.dto.TaskDTO;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface TaskService {
    /**
     * Add a new Task in a project
     * @param taskDTO all information about the task
     * @return TaskDTO containing id and information
     * @throws EntityExistsException if TaskDTO is passed in with an invalid id
     */
    TaskDTO add(TaskDTO taskDTO) throws EntityExistsException;

    /**
     * update an existing task in a project
     * @param taskDTO containing appropriate data for update
     * @return a dto of latest update
     * @throws EntityNotFoundException if no task is associated with id
     */
    TaskDTO update(TaskDTO taskDTO) throws EntityNotFoundException;

    /**
     * delete task
     *
     * @param id task id
     * @return id of deleted task
     * @throws EntityNotFoundException if no task is associated with id
     */
    Integer delete(Integer id) throws EntityNotFoundException;

    /**
     * get a task
     * @param id task id
     * @return dto of the requested task
     * @throws EntityNotFoundException if no task is associated with id
     */
    TaskDTO get(Integer id) throws EntityNotFoundException;

    /**
     * get all task
     * @return list of all task
     */
    List<TaskDTO> getAllProjectTasks(Integer projectId) throws EntityNotFoundException;
}
