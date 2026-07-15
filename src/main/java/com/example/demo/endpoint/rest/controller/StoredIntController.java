package com.example.demo.endpoint.rest.controller;

import com.example.demo.service.S3StoredIntService;
import com.example.demo.service.TmpStoredIntService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class StoredIntController {
  private final TmpStoredIntService tmpStoredIntService;
  private final S3StoredIntService s3StoredIntService;

  @GetMapping("/stored-int")
  public String storedIntTmp() {
    return tmpStoredIntService.getOrCreateStoredInt();
  }

  @GetMapping("/stored-int-s3")
  public String storedIntS3() {
    return s3StoredIntService.getOrCreateStoredInt();
  }
}
