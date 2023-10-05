package org.example;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tassert {
    final static Pattern assertPattern = Pattern.compile(
            "(fail|assert[A-Za-z]+)\\s*\\([A-Za-z0-9,_$()+\\-*/\\s&!|<>=\".{}\\[\\]]*\\)\\s*;"
    );

    /**
     * Count the number of assert statements within a file
     *
     * @param filepath The file to count the assert statements of
     * @return The number of assert statements in the file
     */
    public static int CountAssert(File filepath) {
        CodeParser scanner = new CodeParser(filepath);

        Matcher matcher = assertPattern.matcher(scanner.contents);
        return (int) matcher.results().count();
    }

    public static void main(String[] args) {
        InputOutput io = new InputOutput(args, "Tassert");
        int tassert = CountAssert(io.input_file);

        io.Output(tassert);
    }
}
