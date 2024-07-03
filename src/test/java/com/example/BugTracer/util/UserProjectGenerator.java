package com.example.BugTracer.util;

import com.example.BugTracer.dto.ProjectDTO;
import com.example.BugTracer.dto.UserDTO;
import com.example.BugTracer.dto.UserProjectDTO;
import com.example.BugTracer.model.Role;
import com.example.BugTracer.repo.ProjectRepository;
import com.example.BugTracer.repo.UserRepository;
import com.sb.factorium.FakerGenerator;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class UserProjectGenerator{
    private UserProjectDTO userProjectDTO;
    private UserDTO userDTO;
    private ProjectDTO projectDTO;

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserGenerator userGenerator;
    @Autowired
    private ProjectGenerator projectGenerator;

    UserProjectDTO create() {
        return null;
    }
}
