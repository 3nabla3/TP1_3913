package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

public class Tropcomp {
    /**
     * Returns the 10% highest given a comparison function
     *
     * @param tlsOutputs The list of TlsOutput to compare
     * @param comparator The comparison function
     * @return the element of the array at the 10th percentile
     */
    private static TlsOutput Get10PercentMost(ArrayList<TlsOutput> tlsOutputs, Comparator<TlsOutput> comparator, int seuil) {
        tlsOutputs.sort(comparator);

        // get the index at the top <seuil>th percent and return it
        float percentile = (float) (1 - (seuil / 100.0));
        int index_at_percentile = (int) Math.ceil(tlsOutputs.size() * percentile);
        return tlsOutputs.get(index_at_percentile);
    }

    /**
     * Returns the 10% highest tloc
     *
     * @param tlsOutputs The list of TlsOutput to compare
     * @return the tloc of the element of the array at the 10th percentile
     */
    private static int GetXPercentMostTloc(ArrayList<TlsOutput> tlsOutputs, int seuil) {
        TlsOutput t = Get10PercentMost(tlsOutputs, Comparator.comparingInt(o -> o.tloc), seuil);
        return t.tloc;
    }

    /**
     * Returns the 10% highest tcmp
     *
     * @param tlsOutputs The list of TlsOutput to compare
     * @return the tcmp of the element of the array at the 10th percentile
     */
    private static float GetXPercentMostTcmp(ArrayList<TlsOutput> tlsOutputs, int seuil) {
        TlsOutput t = Get10PercentMost(tlsOutputs, Comparator.comparingDouble(o -> o.tcmp), seuil);
        return t.tcmp;
    }

    public static void main(String[] args) {
        InputOutput io = new InputOutput(args, "Tropcomp");
        File test_dir = new File(io.input_file, "src/test");

        ArrayList<TlsOutput> tlsOutputs = new ArrayList<>();

        ArrayList<File> file_list = Tls.ListAllFiles(test_dir);
        for (File file : file_list) {
            TlsOutput tlsOutput = new TlsOutput(file);
            if (tlsOutput.ContainsTest())
                tlsOutputs.add(tlsOutput);
        }

        int tloc_threshold = GetXPercentMostTloc(tlsOutputs, io.seuil);
        float tcmp_threshold = GetXPercentMostTcmp(tlsOutputs, io.seuil);

        for (TlsOutput tlsOutput : tlsOutputs)
            // if both metrics are above or equal to the threshold, print the file
            if (tlsOutput.tloc >= tloc_threshold && tlsOutput.tcmp >= tcmp_threshold)
                io.Output(tlsOutput);
    }
}