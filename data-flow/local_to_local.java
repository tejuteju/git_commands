package com.bachinalabs;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptors;
import org.joda.time.Duration;
import java.util.*;

public class local_to_local {

    public static void main( String[] args ) {
        PipelineOptions options = PipelineOptionsFactory.create();
        Pipeline p = Pipeline.create(options);
        p.apply("ReadLines", TextIO.read().from("inputData.txt"))
        .apply("WriteCounts", TextIO.write().to("output.txt"));
        p.run();

    }
}
