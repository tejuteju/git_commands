package com.bachinalabs;

import org.apache.beam.runners.dataflow.DataflowRunner;
import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.Watch;
import org.apache.beam.sdk.transforms.windowing.FixedWindows;
import org.apache.beam.sdk.transforms.windowing.Window;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptors;
import org.joda.time.Duration;
import java.util.*;

public class StreamingPipepline {

    public static void main( String[] args ) {

        // Start by defining the options for the pipeline.
        System.out.println(args);
        final List<String> LINES = Arrays.asList(
      "To be, or not to be: that is the question: ",
      "Whether 'tis nobler in the mind to suffer ",
      "The slings and arrows of outrageous fortune, ",
      "Or to take arms against a sea of troubles, ");

        DataflowPipelineOptions options = PipelineOptionsFactory.as(DataflowPipelineOptions.class);
        //PipelineOptions options = PipelineOptionsFactory.create();
        // For Cloud execution, set the Cloud Platform project, staging location,
        // and specify DataflowRunner.
        options.setProject("qwiklabs-gcp-04-c402065b02a8");
        options.setRegion("us-central1");

        // options.setWorkerZone("us-central1-c");
        options.setStagingLocation("gs://qwiklabs-gcp-04-c402065b02a8/binaries/");
        options.setGcpTempLocation("gs://qwiklabs-gcp-04-c402065b02a8/temp/");
        options.setNetwork("default");
        options.setSubnetwork("regions/us-central1/subnetworks/default");
        options.setRunner(DataflowRunner.class);
        options.setNumWorkers(3);

        // Then create the pipeline.
        Pipeline p = Pipeline.create(options);
        p.apply(Create.of(LINES))
        /*p.apply("ReadLines", TextIO.read()
                .from("inputData.txt")*/
                /*.watchForNewFiles(
                        // Check for new files every minute.
                        Duration.standardMinutes(1),
                        // Stop watching the file pattern if no new files appear for an hour.
                        Watch.Growth.afterTimeSinceNewOutput(Duration.standardHours(1))))*/
                
                /*.apply(new SplitWords())
                //.apply(Window.<String>into(FixedWindows.of(Duration.standardMinutes(1))))
                .apply(new CountWords())
                .apply("FormatResults", MapElements
                        .into(TypeDescriptors.strings())
                        .via((KV<String, Long> wordCount) -> wordCount.getKey() + ": " + wordCount.getValue()))*/
                .apply("WriteCounts", TextIO.write().to("gs://qwiklabs-gcp-04-c402065b02a8/gcp-file-out/output.txt"));
                        //.withWindowedWrites().withNumShards(1));


        p.run().waitUntilFinish();

    }
}
