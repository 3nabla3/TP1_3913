package org.example;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tassert {
    final static Pattern pattern = Pattern.compile("assert[A-Za-z]+\\([A-Za-z0-9,_$()\". ]*\\);");

    /**
     * Count the number of assert statements within a file
     *
     * @param filepath The file to count the assert statements of
     * @return The number of assert statements in the file
     */
    public static int CountAssert(File filepath) {
        CodeScanner scanner = new CodeScanner(filepath);
        int count = 0;

        String line;
        while ((line = scanner.nextLine()) != null) {
            Matcher matcher = pattern.matcher(line);

            count += (int) matcher.results().count();
        }
        return count;
    }

    public static void main(String[] args) {
        InputOutput io = new InputOutput(args, "Tassert");
        int tassert = CountAssert(io.input_file);

        io.Output(tassert);
    }
}
