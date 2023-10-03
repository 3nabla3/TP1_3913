package org.example;

import java.io.File;
import java.io.IOException;

public class Tloc {
    public static int GetTloc(File input_file) {
        CodeScanner scanner = new CodeScanner(input_file);

        int tloc = 0;

        String line = scanner.nextLine();
        while (line != null) {
            tloc += 1;
            line = scanner.nextLine();
        }

        return tloc;
    }

    public static void main(String[] args) throws IOException {
        Pair<File, File> parsed_args = Utils.ParseArgs(args);
        File input_file_path = parsed_args.first;
        int tloc = GetTloc(input_file_path);

        System.out.println(tloc);
    }
}