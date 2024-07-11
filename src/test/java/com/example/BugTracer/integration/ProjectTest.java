package com.example.BugTracer.integration;

import com.example.BugTracer.dto.ProjectDTO;
import com.example.BugTracer.service.ProjectService;
import com.example.BugTracer.util.ProjectGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Specifies the ordering strategy
public class ProjectTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private ProjectGenerator projectGenerator;

  @Autowired
  private ProjectService projectService;

  private static final String END_POINT = "/projects";

  /**
   * Test if adding a valid project returns a 200 status code.
   */
  @Test
  @Order(1)
  public void testAddingValidProject() throws Exception {
    ProjectDTO proj = projectGenerator.generate();
    String requestBody = objectMapper.writeValueAsString(proj);

    mockMvc.perform(post(END_POINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
            .andExpect(status().isOk())
            .andDo(print());
  }

  /**
   * Test if requests with invalid formats return appropriate status codes.
   */
  @Test
  public void testInvalidFormatRequests() throws Exception {
    mockMvc.perform(post(END_POINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(""))
            .andExpect(status().isBadRequest())
            .andDo(print());

    mockMvc.perform(get(END_POINT + "/"))
            .andExpect(status().isNotFound())
            .andDo(print());

    mockMvc.perform(delete(END_POINT + "/"))
            .andExpect(status().isNotFound())
            .andDo(print());

    mockMvc.perform(put(END_POINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(""))
            .andExpect(status().isBadRequest())
            .andDo(print());
  }

  /**
   * Test if creating an invalid project (no name, password, email) returns a bad request status.
   */
  @Test
  public void testAddingInvalidProject() throws Exception {
    ProjectDTO invalidProject = new ProjectDTO();
    String requestBody = objectMapper.writeValueAsString(invalidProject);

    mockMvc.perform(post(END_POINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
            .andExpect(status().isBadRequest())
            .andDo(print());
  }

  /**
   * Test if deleting a valid project returns a 200 status code and verifies the project is deleted.
   */
  @Test
  public void testDeleteValidProject() throws Exception {
    projectService.add(projectGenerator.generate());
    Integer projectId = projectService.getAll().get(0).getId();

    mockMvc.perform(delete(END_POINT + "/" + projectId)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print());

    // Assert that entity is not found
    Assertions.assertThrows(EntityNotFoundException.class, () -> projectService.get(projectId));
  }

  /**
   * Test if getting a valid project returns the correct project data.
   */
  @Test
  public void testGetValidProject() throws Exception {
    projectService.add(projectGenerator.generate());
    ProjectDTO projectDTO = projectService.getAll().get(0);

    mockMvc.perform(get(END_POINT + "/" + projectDTO.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(projectDTO.getName())))
            .andDo(print());
  }

  /**
   * Test if updating a valid project returns the correct updated project data.
   */
  @Test
  public void testUpdateValidProject() throws Exception {
    projectService.add(projectGenerator.generate());
    ProjectDTO projectDTO = projectService.getAll().get(0);
    ProjectDTO updatedProject = projectGenerator.generate();
    updatedProject.setId(projectDTO.getId());

    String requestBody = objectMapper.writeValueAsString(updatedProject);

    mockMvc.perform(put(END_POINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(updatedProject.getName())))
            .andDo(print());
  }
}
