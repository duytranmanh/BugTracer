package com.example.BugTracer.integration;

import com.example.BugTracer.dto.ProjectDTO;
import com.example.BugTracer.service.ProjectService;
import com.example.BugTracer.util.ProjectGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // This line specifies the ordering strategy
public class UserProjectTest {

    private final MockMvc mockMvc;


    private final ObjectMapper objectMapper;


    private final ProjectGenerator projectGenerator;


    private final ProjectService projectService;

    private static final String END_POINT = "/projects";

    @Autowired
    public UserProjectTest(MockMvc mockMvc, ObjectMapper objectMapper, ProjectGenerator projectGenerator,
                           ProjectService projectService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.projectGenerator = projectGenerator;
        this.projectService = projectService;
    }

    /**
     * This test all request to ProjectController with invalid format (body not in JSON, missing
     * param) returns
     * @throws Exception
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
                        .contentType("application/json")
                        .content(""))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    /**
     * this test tests if adding valid user comes back with 200 code
     * @throws Exception
     */
    @Test
    @Order(1)
    public void testAddingValidUserProject() throws Exception {
        ProjectDTO proj = projectGenerator.generate();

        String name = proj.getName();

        String requestBody = objectMapper.writeValueAsString(proj);

        mockMvc.perform(post(END_POINT)
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
//        .andExpectAll(jsonPath("$.name", is(name)))
                .andDo(print());
    }

    /**
     * This test tests if creating an invalid user(no name, password, email) returns
     * @throws Exception
     */
    @Test
    public void testAddingInvalidUserProject() throws Exception {
        ProjectDTO invalidProject = new ProjectDTO();

        String requestBody = objectMapper.writeValueAsString(invalidProject);

        mockMvc.perform(post(END_POINT)
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    /**
     * this test tests if deleting a valid user returns a 200 http code
     * also checks if get user with the same userid throws an exception
     * @throws Exception
     */
    @Test
    public void testDeleteValidUserProject() throws Exception {
        projectService.add(projectGenerator.generate());
        Integer projectId = projectService.getAll().get(0).getId();

        mockMvc.perform(delete(END_POINT + "/" + projectId)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andDo(print());

        //Assert that entity is not found
        Assertions.assertThrows(EntityNotFoundException.class,() -> projectService.get(projectId));
    }

    /**
     * This test tests if getting a valid user(no name, password, email) returns correct user
     * @throws Exception
     */
    @Test
    public void testGetValidUserProject() throws Exception {
        projectService.add(projectGenerator.generate());
        ProjectDTO projectDTO = projectService.getAll().get(0);

        mockMvc.perform(get(END_POINT + "/" + projectDTO.getId()))
                .andExpect(status().isOk())
                .andExpectAll(jsonPath("$.name", is(projectDTO.getName())))
                .andDo(print());
    }


    /**
     * This test tests if updating a valid user(no name, password, email) returns correct updates
     * @throws Exception
     */
    @Test
    public void testUpdateValidUserProject() throws Exception{
        projectService.add(projectGenerator.generate());
        ProjectDTO projectDTO = projectService.getAll().get(0);
        ProjectDTO updatedProject = projectGenerator.generate();
        updatedProject.setId(projectDTO.getId());

        String requestBody = objectMapper.writeValueAsString(updatedProject);

        mockMvc.perform(put(END_POINT)
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpectAll(jsonPath("$.name",
                        is(updatedProject.getName())))
                .andDo(print());

    }

}
