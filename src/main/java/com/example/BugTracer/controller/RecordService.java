package com.example.BugTracer.controller;

import com.example.BugTracer.service.impl.AddRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/record")
public class RecordService {

  private AddRecordService addRecordService;

  public RecordService(AddRecordService addRecordService) {
    this.addRecordService = addRecordService;
  }

  @PostMapping(value = "/{num}")
  public String AddRecord(@PathVariable("num") int num) {
    addRecordService.insertRecords(num);
    return "successful";
  }
}
