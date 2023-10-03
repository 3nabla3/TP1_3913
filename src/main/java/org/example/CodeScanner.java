package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CodeScanner {
    Scanner scanner;

    public CodeScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public CodeScanner(File file) throws FileNotFoundException {
        this.scanner = new Scanner(file);
    }

    public String nextLine() {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = line.strip();

            // whitespace
            if (line.isEmpty()) continue;
            // single line comment
            if (line.startsWith("//")) continue;
            // start block comment
            if (line.startsWith("/*")) continue;
            // block comment
            if (line.startsWith("*")) continue;
            // end block comment
            if (line.startsWith("*/")) continue;

            return line;
        }

        return null;
    }
}
