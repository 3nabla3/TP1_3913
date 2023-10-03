package org.example;

import java.io.IOException;

public class Tloc {
    public static int GetTloc(CodeScanner scanner) {
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
        System.out.println("input_file_path = " + input_file_path);
        String output_file_path = parsed_args.second;
        System.out.println("output_file_path = " + output_file_path);

        CodeScanner scanner = new CodeScanner(input_file_path);

        int tloc = GetTloc(scanner);
        System.out.println("tloc = " + tloc);
    }
}