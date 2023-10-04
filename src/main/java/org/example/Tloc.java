package org.example;

import java.io.File;

public class Tloc {
    /**
     * Count the number of lines of code in a file
     *
     * @param input_file The file to count the lines of code of
     * @return The number of lines of code in the file
     */
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

    public static void main(String[] args) {
        InputOutput io = new InputOutput(args, "Tloc");
        int tloc = GetTloc(io.input_file);

        io.Output(tloc);
    }
}