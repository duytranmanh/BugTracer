package com.example.BugTracer.util;

import com.example.BugTracer.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

/**
 * Helper class for generating test subjects
 */
@Component
public class Generate {
  private final String MAIL_DOMAIN = "@supermail.com";
  private Random random;

  public Generate() {
    random = new Random();
  }

  //Generate a User with valid username and email
  public UserDTO userDTO() {
    String username = "" + random.nextInt();
    String password = "" + random.nextInt() + 10000000;
    String email = username + MAIL_DOMAIN;

    UserDTO genUser = new UserDTO();
    genUser.setUsername(username);
    genUser.setEmail(email);
    genUser.setPassword(password);

    return genUser;
  }
}
