package org.example;

import java.io.File;

public class Tassert {
    public static int CountAssert(File filepath) {
        CodeScanner scanner = new CodeScanner(filepath);
        int count = 0;

        String line;
        while ((line = scanner.nextLine()) != null) {
            if ((line.contains("assert") || line.contains("fail")) && !line.contains("//") && !line.contains("import")) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        InputOutput io = new InputOutput(args);
        int tassert = CountAssert(io.input_file);

        io.Output(tassert);
    }
}
