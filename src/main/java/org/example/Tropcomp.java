package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

public class Tropcomp {
    /**
     * Returns the 10% highest given a comparison function
     * @param tlsOutputs The list of TlsOutput to compare
     * @param comparator The comparison function
     * @return the element of the array at the 10th percentile
     */
    private static TlsOutput Get10PercentMost(ArrayList<TlsOutput> tlsOutputs, Comparator<TlsOutput> comparator) {
        tlsOutputs.sort(comparator);

        // get the index at the 10th percentile and return it
        int ten_percent = (int) Math.ceil(tlsOutputs.size() * 0.1);
        return tlsOutputs.get(ten_percent);
    }

    /**
     * Returns the 10% highest tloc
     * @param tlsOutputs The list of TlsOutput to compare
     * @return the tloc of the element of the array at the 10th percentile
     */
    private static int Get10PercentMostTloc(ArrayList<TlsOutput> tlsOutputs) {
        TlsOutput t = Get10PercentMost(tlsOutputs, Comparator.comparingInt(o -> o.tloc));
        return t.tloc;
    }

    /**
     * Returns the 10% highest tcmp
     * @param tlsOutputs The list of TlsOutput to compare
     * @return the tcmp of the element of the array at the 10th percentile
     */
    private static float Get10PercentMostTcmp(ArrayList<TlsOutput> tlsOutputs) {
        TlsOutput t = Get10PercentMost(tlsOutputs, Comparator.comparingDouble(o -> o.tcmp));
        return t.tcmp;
    }

    public static void main(String[] args) {
        Pair<File, File> parsed_args = Utils.ParseArgs(args);
        File project_dir = parsed_args.first;
        File test_dir = new File(project_dir, "src/test");

        ArrayList<TlsOutput> tlsOutputs = new ArrayList<>();

        ArrayList<File> file_list = Tls.ListAllFiles(test_dir);
        for (File file : file_list) {
            TlsOutput tlsOutput = new TlsOutput(file);
            if (tlsOutput.ContainsTest())
                tlsOutputs.add(tlsOutput);
        }

        int tloc_threshold = Get10PercentMostTloc(tlsOutputs);
        float tcmp_threshold = Get10PercentMostTcmp(tlsOutputs);

        for (TlsOutput tlsOutput : tlsOutputs)
            // if both metics are above the threshold, print the file
            if (tlsOutput.tloc > tloc_threshold && tlsOutput.tcmp > tcmp_threshold)
                System.out.println(tlsOutput);
    }
}