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
        InputOutput io = new InputOutput(args);
        int tloc = GetTloc(io.input_file);

        io.Output(tloc);
    }
}