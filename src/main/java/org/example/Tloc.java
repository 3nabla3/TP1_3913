package org.example;

import java.io.IOException;

public class Tloc {
    public static int GetTloc(String input_file_path) {
        CodeScanner scanner = new CodeScanner(input_file_path);

        int tloc = 0;

        String line = scanner.nextLine();
        while (line != null) {
            tloc += 1;
            line = scanner.nextLine();
        }

        return tloc;
    }

    public static void main(String[] args) throws IOException {
        Pair<String, String> parsed_args = Utils.ParseArgs(args);
        String input_file_path = parsed_args.first;
        int tloc = GetTloc(input_file_path);

        System.out.println(tloc);
    }
}