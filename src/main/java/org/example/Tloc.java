package org.example;

import java.io.File;
import java.util.Arrays;

public class Tloc {
    /**
     * Count the number of lines of code in a file
     *
     * @param input_file The file to count the lines of code of
     * @return The number of lines of code in the file
     */
    public static int GetTloc(File input_file) {
        CodeParser scanner = new CodeParser(input_file);
        String contents = scanner.contents;

        int count = 0;
        for (String line: contents.split("\n")) {
            if (line.trim().isEmpty()) continue;
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        InputOutput io = new InputOutput(args, "Tloc");
        int tloc = GetTloc(io.input_file);

        io.Output(tloc);
    }
}