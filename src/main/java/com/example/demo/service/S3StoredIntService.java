package com.example.demo.service;

import static java.io.File.createTempFile;

import com.example.demo.file.bucket.BucketComponent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class S3StoredIntService {

  private static final String BUCKET_KEY = "stored-int.txt";

  private final BucketComponent bucketComponent;

  @SneakyThrows
  public String getOrCreateStoredInt() {
    if (existsInBucket()) {
      var downloadedFile = bucketComponent.download(BUCKET_KEY);
      return Files.readString(downloadedFile.toPath()).trim();
    }

    var randomInt = new Random().nextInt(1_000_000);
    var fileToUpload = createTempFile("stored-int", ".txt");
    writeMessageIntoFile(String.valueOf(randomInt), fileToUpload);
    bucketComponent.upload(fileToUpload, BUCKET_KEY);
    return String.valueOf(randomInt);
  }

  private boolean existsInBucket() {
    try {
      bucketComponent.download(BUCKET_KEY);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private void writeMessageIntoFile(String message, File file) throws IOException {
    FileWriter writer = new FileWriter(file);
    writer.write(message);
    writer.close();
  }
}
