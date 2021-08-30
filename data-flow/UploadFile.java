package com.bachinalabs;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UploadFile {
  public static void main(String [] args)throws IOException {
    // The ID of your GCP project
    String projectId = "dataflow-320606";

    // The ID to give your GCS bucket
    String bucketName = "quiet-groove-317205-bucket";

    // The ID of your GCS object
    String objectName = "local1";

    // The path to your file to upload
    String filePath = "inputData.csv";

    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
    BlobId blobId = BlobId.of(bucketName, objectName);
    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
    storage.create(blobInfo, Files.readAllBytes(Paths.get(filePath)));

    System.out.println(
        "File " + filePath + " uploaded to bucket " + bucketName + " as " + objectName);
  }
}