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

public class Ex {

    public static void main( String[] args ) {
        final List<String> LINES = Arrays.asList(
      "To be, or not to be: that is the question: ",
      "Whether 'tis nobler in the mind to suffer ",
      "The slings and arrows of outrageous fortune, ",
      "Or to take arms against a sea of troubles, ");

        DataflowPipelineOptions options = PipelineOptionsFactory.as(DataflowPipelineOptions.class);
        options.setProject("quiet-groove-317205");
        options.setRegion("us-central1");
        // options.setWorkerZone("us-central1-c");
        options.setStagingLocation("gs://quiet-groove-317205/binaries/");
        options.setGcpTempLocation("gs://quiet-groove-317205/temp/");
        options.setNetwork("default");
        options.setSubnetwork("regions/us-central1/subnetworks/default");
        options.setRunner(DataflowRunner.class);
        options.setNumWorkers(3);

        Pipeline p = Pipeline.create(options);
        p.apply(Create.of(LINES))
        .apply("WriteCounts", TextIO.write().to("gs://quiet-groove-317205/gcp-file-out/output.txt"));
        p.run().waitUntilFinish();

    }
}
