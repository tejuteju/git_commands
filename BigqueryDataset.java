package com.bachinalabs;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryException;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.Dataset;
import com.google.cloud.bigquery.DatasetInfo;

public class BigqueryDataset {

  public static void main(String[] args) {
    // TODO(developer): Replace these variables before running the sample.
    String projectId = "dataflow-320606";
    String datasetName = "dataset";
    createDataset(projectId,datasetName);
  }

  public static void createDataset(String projectId,String datasetName) {
    try {
      // Initialize client that will be used to send requests. This client only needs to be created
      // once, and can be reused for multiple requests.
      BigQuery bigquery = BigQueryOptions.newBuilder().setProjectId(projectId).build().getService();

      DatasetInfo datasetInfo = DatasetInfo.newBuilder(datasetName).build();

      Dataset newDataset = bigquery.create(datasetInfo);
      String newDatasetName = newDataset.getDatasetId().getDataset();
      System.out.println(newDatasetName + " created successfully");
    } catch (BigQueryException e) {
      System.out.println("Dataset was not created. \n" + e.toString());
    }
  }
}