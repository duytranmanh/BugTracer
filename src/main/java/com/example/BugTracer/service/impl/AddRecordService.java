package com.example.BugTracer.service.impl;


import com.example.BugTracer.model.User;
import com.example.BugTracer.repo.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AddRecordService {

  private UserRepository userRepository;

  public AddRecordService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
//  @Transactional
//  public void insertRecords() {
//    String sql = "INSERT INTO USERS (username, email, password) VALUES (?, ?, ?)";
//    List<Object[]> batchArgs = new ArrayList<>();
//    for (int i = 0; i < 100000; i++) {
//      batchArgs.add(new Object[]{random.nextInt(), random.nextInt(), random.nextInt()});
//    }
//    jdbcTemplate.batchUpdate(sql, batchArgs);
//  }

  @Transactional
  public void insertRecords(int recordNum) {

    List<User> userList = new ArrayList<>();
//    Faker faker = new Faker();

    for (int i = 0; i < recordNum; i++) {
      User user = new User();

      user.setUsername("user" + i);
      user.setPassword(i + "1000000000");
      user.setEmail(i + "@mail.com");
//      userRepository.save(user);
      userList.add(user);
    }

    userRepository.saveAll(userList);
  }

  public void insertRecords() {
//    userRepository.deleteAll();
//
//    List<User> userList = new ArrayList<>();
//    Faker faker = new Faker();
//
//    for (int i = 0; i < 1; i++) {
//      User user = new User();
//
//      user.setUsername("user" + i);
//      user.setPassword(i + "100000");
//      user.setEmail(i + "@mail.com");
//      userList.add(user);
//    }
//
//    userRepository.saveAll(userList);
    userRepository.deleteAll();

    User user = new User();
    user.setUsername("user");
    user.setPassword( "10000011111");
    user.setEmail( "e@mail.com");

    userRepository.save(user);
  }
}

