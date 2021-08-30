package com.bachinalabs;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class QuickstartSample {
  public static void main(String... args) throws Exception {
    // Instantiates a client
    Storage storage = StorageOptions.getDefaultInstance().getService();

    // The name for the new bucket
    String bucketName = "quiet-groove-bucket";  // "my-new-bucket";

    // Creates the new bucket
    Bucket bucket = storage.create(BucketInfo.of(bucketName));

    System.out.printf("Bucket %s created.%n", bucket.getName());
  }
}