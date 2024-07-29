package com.example.BugTracer.service.impl;

import com.example.BugTracer.dto.CommentDTO;
import com.example.BugTracer.model.Comment;
import com.example.BugTracer.model.Task;
import com.example.BugTracer.model.User;
import com.example.BugTracer.repo.CommentRepository;
import com.example.BugTracer.repo.TaskRepository;
import com.example.BugTracer.repo.UserRepository;
import com.example.BugTracer.service.CommentService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TypeMap<CommentDTO, Comment> toCommentTypeMap;
    private final TypeMap<Comment, CommentDTO> toCommentDTOTypeMap;
    private final String errorMessage = "comment";

    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper, TaskRepository taskRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;

        this.modelMapper.getConfiguration().setImplicitMappingEnabled(false);

        toCommentTypeMap = modelMapper.createTypeMap(CommentDTO.class, Comment.class);
        toCommentDTOTypeMap = modelMapper.createTypeMap(Comment.class, CommentDTO.class);

        //create converter
        Converter<Integer, User> toUserConverter = id -> userRepository.getReferenceById(id.getSource());
        Converter<User, Integer> toUserIdConverter = user -> user.getSource().getId();

        Converter<Integer, Task> toTaskConverter = id -> taskRepository.getReferenceById(id.getSource());
        Converter<Task, Integer> toTaskIdConverter = task -> task.getSource().getId();

        //bind converter to typeMaps
        toCommentTypeMap.addMappings(mapping -> mapping.using(toUserConverter).map(CommentDTO::getAuthorId, Comment::setAuthor));
        toCommentTypeMap.addMappings(mapping -> mapping.using(toTaskConverter).map(CommentDTO::getTaskId, Comment::setTask));

        toCommentDTOTypeMap.addMappings(mapping -> mapping.using(toUserIdConverter).map(Comment::getAuthor, CommentDTO::setAuthorId));
        toCommentDTOTypeMap.addMappings(mapping -> mapping.using(toTaskIdConverter).map(Comment::getTask, CommentDTO::setTaskId));

        //call implicit mappings
        toCommentDTOTypeMap.implicitMappings();
        toCommentTypeMap.implicitMappings();

    }

    /**
     * Add a new comment in a task
     *
     * @param commentDTO all information about the comment
     * @return CommentDTO containing id and information
     * @throws EntityExistsException if CommentDto is passed in with an invalid id
     */
    @Override
    public CommentDTO add(CommentDTO commentDTO) throws EntityExistsException {
        if (checkExistence(commentDTO)) throw new EntityExistsException(errorMessage);

        Comment comment = modelMapper.map(commentDTO, Comment.class);

        return modelMapper.map(commentRepository.save(comment), CommentDTO.class);
    }



    /**
     * update an existing comment in a task
     *
     * @param commentDTO containing appropriate data for update
     * @return a dto of latest update
     * @throws EntityNotFoundException if no task is associated with id
     */
    @Override
    public CommentDTO update(CommentDTO commentDTO) throws EntityNotFoundException {
        if (!checkExistence(commentDTO)) throw new EntityNotFoundException(errorMessage);

        Provider<Comment> commentProvider = p -> commentRepository.getReferenceById(commentDTO.getId());
        toCommentTypeMap.setProvider(commentProvider);

        return modelMapper.map(commentRepository.save(modelMapper.map(commentDTO, Comment.class)), CommentDTO.class);
    }

    /**
     * delete an existing comment in a task
     *
     * @param id comment id
     * @return id of the deleted comment
     * @throws EntityNotFoundException if no comment is associated with id
     */
    @Override
    public Integer delete(Integer id) throws EntityNotFoundException {
        if (!checkExistence(id)) throw new EntityNotFoundException(errorMessage);
        commentRepository.deleteById(id);
        return id;
    }

    /**
     * get a comment
     *
     * @param id comment id
     * @return dto of the requested task
     * @throws EntityNotFoundException if no comment is associated with id
     */
    @Override
    public CommentDTO get(Integer id) throws EntityNotFoundException {
        if (!checkExistence(id)) throw new EntityNotFoundException(errorMessage);
        return modelMapper.map(commentRepository.getReferenceById(id), CommentDTO.class);
    }

    /**
     * get all comment in a task
     *
     * @param taskId
     * @return list of all task
     */
    @Override
    public List<CommentDTO> getAllCommentInTask(Integer taskId) throws EntityNotFoundException {
        if (!taskRepository.existsById(taskId)) throw new EntityNotFoundException("task");

        List<Comment> commentList = taskRepository.getReferenceById(taskId).getCommentList();
        List<CommentDTO> commentDTOList = new ArrayList<>();

        for (Comment comment : commentList) {
            commentDTOList.add(modelMapper.map(comment, CommentDTO.class));
        }

        return commentDTOList;
    }

    /**
     * private helper that check if a comment existed or not
     * @param commentDTO
     */
    private boolean checkExistence(CommentDTO commentDTO) {
        if (commentDTO.getId() == null) return false;
        return commentRepository.existsById(commentDTO.getId());
    }

    private boolean checkExistence(Integer id) {
        if (id == null) return false;
        return commentRepository.existsById(id);
    }
}
