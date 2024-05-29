package com.example.BugTracer.integration;

import com.example.BugTracer.controller.UserController;
import com.example.BugTracer.dto.UserDTO;
import com.example.BugTracer.dto.UserProjectDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  private static final Logger LOGGER = LoggerFactory.getLogger(UserTest.class);
  private static final String END_POINT = "/user";
//  private static int
//  private UserDTO generateDTOwUsername() {
//    return
//  }

  @Test
  public void testAddingValidUser() throws Exception {
    UserDTO userDTO = new UserDTO();
    userDTO.setUsername("validname2");
    userDTO.setEmail("valid@mail.com");
    userDTO.setPassword("supervalidpassword123");

    String requestBody = objectMapper.writeValueAsString(userDTO);

    mockMvc.perform(post(END_POINT)
            .contentType("application/json")
            .content(requestBody))
        .andExpect(status().isOk())
        .andDo(print());

  }
}
