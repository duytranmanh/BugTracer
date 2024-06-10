package com.example.BugTracer.unit;

import com.example.BugTracer.controller.UserController;
import com.example.BugTracer.dto.UserDTO;
import com.example.BugTracer.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
//@SpringBootTest
//@AutoConfigureMockMvc
public class UserApiControllerTest {
  private static final String END_POINT = "/user";
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @MockBean
  private UserService userService;

  /**
   * test case: adding an empty user
   * should return a http status of BAD_REQUEST
   * message: "password cannot be empty username cannot be empty email cannot be empty "
   *
   * @throws Exception
   */
  @Test
  public void testAddingInvalidUser() throws Exception {
    UserDTO userDTO = new UserDTO();

    String requestBody = objectMapper.writeValueAsString(userDTO);

    mockMvc.perform(post(END_POINT)
            .contentType("application/json")
            .content(requestBody)
        )
        .andExpect(status().isBadRequest())
        .andDo(print());

  }

  @Test
  public void testAddingValidUser() throws Exception {
    //creating a valid dto
    UserDTO userDTO = new UserDTO();
    userDTO.setUsername("valid name");
    userDTO.setEmail("valid@mail.com");
    userDTO.setPassword("supervalidpassword123");

    //converting dto to JSON (this is the request body)
    String requestBody = objectMapper.writeValueAsString(userDTO);


    //creating a dto returned from the service call
    UserDTO returnedDTO = new UserDTO();
    returnedDTO.setUsername("valid name");
    returnedDTO.setEmail("valid@mail.com");
    returnedDTO.setPassword(null);

    //mocking the userService call
    Mockito.when(userService.add(userDTO)).thenReturn(returnedDTO);

    //creating an expected response body
    String expectedReturnBody = objectMapper.writeValueAsString(returnedDTO);

    //perform the request
    mockMvc.perform(post(END_POINT)
            .contentType("application/json")
            .content(requestBody)
        )
        //checking if the status is OK
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        //checking if response body is the same as expected
        .andExpect(content().json(expectedReturnBody))
        .andDo(print());

    //assert
    Mockito.verify(userService, times(1)).add(userDTO);

  }

}
