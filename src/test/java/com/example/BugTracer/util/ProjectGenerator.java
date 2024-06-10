package com.example.BugTracer.util;

import com.example.BugTracer.dto.ProjectDTO;
import com.example.BugTracer.dto.UserDTO;
import com.example.BugTracer.model.Project;
import com.sb.factorium.FakerGenerator;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class ProjectGenerator extends FakerGenerator<ProjectDTO> {
  private final Faker faker = new Faker();
  @Override
  protected ProjectDTO make() {
    ProjectDTO projectDTO = new ProjectDTO();
    String name = faker.company().name();

    projectDTO.setName(name);

    return projectDTO;
  }
}
