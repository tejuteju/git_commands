package com.bachinalabs;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageClass;
import com.google.cloud.storage.StorageOptions;

public class StorageBucket {
  public static void main(String[] args) throws Exception {
    // The ID of your GCP project
    String projectId = "dataflow-320606";

    // The ID to give your GCS bucket
    String bucketName = "quiet-groove-317205-bucket";

    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();

    // See the StorageClass documentation for other valid storage classes:
    // https://googleapis.dev/java/google-cloud-clients/latest/com/google/cloud/storage/StorageClass.html
    StorageClass storageClass = StorageClass.COLDLINE;

    // See this documentation for other valid locations:
    // http://g.co/cloud/storage/docs/bucket-locations#location-mr
    String location = "ASIA";

    Bucket bucket =
        storage.create(
            BucketInfo.newBuilder(bucketName)
                .setStorageClass(storageClass)
                .setLocation(location)
                .build());

    System.out.println(
        "Created bucket "
            + bucket.getName()
            + " in "
            + bucket.getLocation()
            + " with storage class "
            + bucket.getStorageClass());
  }
}