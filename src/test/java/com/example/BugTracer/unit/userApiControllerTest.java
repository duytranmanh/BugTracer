package com.example.BugTracer.unit;

import com.example.BugTracer.controller.UserController;
import com.example.BugTracer.dto.UserDTO;
import com.example.BugTracer.model.User;
import com.example.BugTracer.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class userApiControllerTest {
  private final String END_POINT = "/user";
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @MockBean
  private UserService userService;

  @Test
  public void testAddingValidUser() throws Exception {
    UserDTO userDTO = new UserDTO();

    String requestBody = objectMapper.writeValueAsString(userDTO);

    mockMvc.perform(post(END_POINT).contentType("application/json")
        .content(requestBody))
        .andExpectAll(status().isBadRequest())
        .andDo(print());

  }


}
