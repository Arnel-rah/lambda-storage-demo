package com.example.demo.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class TmpStoredIntService {
  private static final String FILE_PATH = "/tmp/stored_int.txt";

  @SneakyThrows
  public String getOrCreateStoredInt() {
    var file = new File(FILE_PATH);
    if (file.exists()) {
      return Files.readString(file.toPath()).trim();
    }
    var randomInt = new Random().nextInt(10000);
    writeMessageIntoFile(String.valueOf(randomInt), file);
    return String.valueOf(randomInt);
  }

  private void writeMessageIntoFile(String message, File file) throws IOException {
    FileWriter writer = new FileWriter(file);
    writer.write(message);
    writer.close();
  }
}
