package org.example;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


/**
 * Takes in a filename and returns each line of code one by one
 */
public class CodeParser {
    String contents;
    int index = 0;

    // stolen from https://stackoverflow.com/a/1740692
    final String commentRegex = "//.*|(\"(?:\\\\[^\"]|\\\\\"|.)*?\")|(?s)/\\*.*?\\*/";

    private void Init(String filepath) {
        try {
            contents = Files.readString(Paths.get(filepath));
        } catch (java.io.IOException e) {
            System.out.println("Could not open file " + filepath);
            System.exit(1);
        }

        contents = contents.replaceAll(commentRegex, " ");
    }

    public CodeParser(File file) {
        Init(file.getPath());
    }
}
