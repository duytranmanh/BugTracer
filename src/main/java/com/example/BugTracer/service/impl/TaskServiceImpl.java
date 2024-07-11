package com.example.BugTracer.service.impl;

import com.example.BugTracer.dto.TaskDTO;
import com.example.BugTracer.model.Project;
import com.example.BugTracer.model.Task;
import com.example.BugTracer.model.User;
import com.example.BugTracer.repo.ProjectRepository;
import com.example.BugTracer.repo.TaskRepository;
import com.example.BugTracer.repo.UserRepository;
import com.example.BugTracer.service.TaskService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    private final TypeMap<TaskDTO, Task> toTaskTypeMap;
    private final TypeMap<Task, TaskDTO> toTaskDTOMap;
    private final String exceptionMessage = "task";

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository, ProjectRepository projectRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;

        this.modelMapper.getConfiguration().setImplicitMappingEnabled(false);

        toTaskTypeMap = modelMapper.createTypeMap(TaskDTO.class, Task.class);
        toTaskDTOMap = modelMapper.createTypeMap(Task.class, TaskDTO.class);


        //converter for author and project
        Converter<Integer, User> toUserConverter = id -> this.userRepository.getReferenceById(id.getSource());
        Converter<User, Integer> toUserIdConverter = user -> user.getSource().getId();

        Converter<Integer, Project> toProjectConverter = id -> this.projectRepository.getReferenceById(id.getSource());
        Converter<Project, Integer> toProjectIdConverter = prj -> prj.getSource().getId();


        //binding converter to appropriate properties mapping
        toTaskTypeMap.addMappings(mapper -> mapper.using(toUserConverter).map(TaskDTO::getAuthorId, Task::setAuthor));
        toTaskTypeMap.addMappings(mapper -> mapper.using(toProjectConverter).map(TaskDTO::getProjectId, Task::setProject));

        toTaskDTOMap.addMappings(mapper -> mapper.using(toUserIdConverter).map(Task::getAuthor, TaskDTO::setAuthorId));
        toTaskDTOMap.addMappings(mapper -> mapper.using(toProjectIdConverter).map(Task::getProject, TaskDTO::setProjectId));

        toTaskTypeMap.implicitMappings();
        toTaskDTOMap.implicitMappings();

    }

    /**
     * Add a new Task in a project
     *
     * @param taskDTO all information about the task
     * @return TaskDTO containing id and information
     * @throws EntityExistsException if TaskDTO is passed in with an invalid id
     */
    @Override
    public TaskDTO add(TaskDTO taskDTO) throws EntityExistsException {
        //check for existence
        if (checkExistence(taskDTO)) throw new EntityExistsException(exceptionMessage);

        Task task = modelMapper.map(taskDTO, Task.class);

        return modelMapper.map(taskRepository.save(task), TaskDTO.class);
    }

    /**
     * update an existing task in a project
     *
     * @param taskDTO containing appropriate data for update
     * @return a dto of latest update
     * @throws EntityNotFoundException if no task is associated with id
     */
    @Override
    public TaskDTO update(TaskDTO taskDTO) throws EntityNotFoundException {
        if (!checkExistence(taskDTO)) throw new EntityNotFoundException(exceptionMessage);

        Provider<Task> taskProvider = p -> taskRepository.getReferenceById(taskDTO.getId());
        toTaskTypeMap.setProvider(taskProvider);

        return modelMapper.map(taskRepository.save(modelMapper.map(taskDTO, Task.class)), TaskDTO.class);
    }

    /**
     * delete task
     *
     * @param id task id
     * @return id of deleted task
     * @throws EntityNotFoundException if no task is associated with id
     */
    @Override
    public Integer delete(Integer id) throws EntityNotFoundException {
        if (!checkExistence(id)) throw new EntityNotFoundException(exceptionMessage);
        taskRepository.deleteById(id);
        return id;
    }

    /**
     * get a task
     *
     * @param id task id
     * @return dto of the requested task
     * @throws EntityNotFoundException if no task is associated with id
     */
    @Override
    public TaskDTO get(Integer id) throws EntityNotFoundException {
        if (!checkExistence(id)) throw new EntityNotFoundException(exceptionMessage);
        return modelMapper.map(taskRepository.getReferenceById(id), TaskDTO.class);
    }

    /**
     * get all task
     *
     * @param projectId
     * @return list of all task
     */
    @Override
    public List<TaskDTO> getAllProjectTasks(Integer projectId) throws EntityNotFoundException {
        //if project does not exist throw exception
        if (!projectRepository.existsById(projectId)) throw new EntityNotFoundException("project");

        List<TaskDTO> dtoList = new ArrayList<>();

        for (Task task: taskRepository.findAllByProject(projectRepository.getReferenceById(projectId))) {
            dtoList.add(modelMapper.map(task, TaskDTO.class));
        }

        return dtoList;
    }

    /**
     * private helper method that checks if a task already exists
     * @param taskDTO
     */
    private boolean checkExistence(TaskDTO taskDTO) {
        if (taskDTO.getId() == null) return false;
        return taskRepository.existsById(taskDTO.getId());
    }

    /**
     * private helper method that checks if a task already exists
     * @param id
     */
    private boolean checkExistence(Integer id) {
        return taskRepository.existsById(id);
    }
}
