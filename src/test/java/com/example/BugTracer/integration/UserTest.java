package com.example.BugTracer.integration;

import com.example.BugTracer.dto.UserDTO;
import com.example.BugTracer.service.UserService;
import com.example.BugTracer.util.UserGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This integration test the function of the application when making api calls in UserController
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private UserGenerator userGenerator;

  @Autowired
  private UserService userService;

  private static final Logger LOGGER = LoggerFactory.getLogger(UserTest.class);
  private static final String END_POINT = "/user";


  /**
   * This test all request to UserController with invalid format (body not in JSON, missing param)
   * @throws Exception
   */
  @Test
  public void testInvalidFormatRequests() throws Exception {
    mockMvc.perform(post(END_POINT)
            .contentType("application/json")
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
  public void testAddingValidUser() throws Exception {
    UserDTO userDTO = userGenerator.generate();

    String email = userDTO.getEmail();
    String username = userDTO.getUsername();

    String requestBody = objectMapper.writeValueAsString(userDTO);

    mockMvc.perform(post(END_POINT)
            .contentType("application/json")
            .content(requestBody))
        .andExpect(status().isOk())
        .andExpectAll(jsonPath("$.email", is(email)), jsonPath("$.username", is(username)))
        .andDo(print());

    Assertions.assertEquals(userDTO, userService.getByUsername(username));
  }

  /**
   * This test tests if creating an invalid user(no name, password, email) returns
   * @throws Exception
   */
  @Test
  public void testAddingInvalidUser() throws Exception {
    UserDTO invalidUser = new UserDTO();

    String requestBody = objectMapper.writeValueAsString(invalidUser);

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
  public void testDeleteValidUser() throws Exception {
    userService.add(userGenerator.generate());
    Integer userId = userService.getAll().get(0).getId();

    mockMvc.perform(delete(END_POINT + "/" + userId)
            .contentType("application/json"))
        .andExpect(status().isOk())
        .andDo(print());

    //Assert that entity is not found
    Assertions.assertThrows(EntityNotFoundException.class,() -> userService.get(userId));
  }

  /**
   * This test tests if getting a valid user(no name, password, email) returns correct user
   * @throws Exception
   */
  @Test
  public void testGetValidUser() throws Exception {
    userService.add(userGenerator.generate());
    UserDTO userDTO = userService.getAll().get(0);

    mockMvc.perform(get(END_POINT + "/" + userDTO.getId()))
        .andExpect(status().isOk())
        .andExpectAll(jsonPath("$.email", is(userDTO.getEmail())), jsonPath("$.username",
            is(userDTO.getUsername())))
        .andDo(print());
  }


  /**
   * This test tests if updating a valid user(no name, password, email) returns correct updates
   * @throws Exception
   */
  @Test
  public void testUpdateValidUser() throws Exception{
    userService.add(userGenerator.generate());
    UserDTO userDTO = userService.getAll().get(0);
    UserDTO updatedUser = userGenerator.generate();
    updatedUser.setId(userDTO.getId());

    String requestBody = objectMapper.writeValueAsString(updatedUser);

    mockMvc.perform(post(END_POINT)
            .contentType("application/json")
            .content(requestBody))
        .andExpect(status().isOk())
        .andExpectAll(jsonPath("$.email", is(updatedUser.getEmail())), jsonPath("$.username",
            is(updatedUser.getUsername())))
        .andDo(print());

  }

}
