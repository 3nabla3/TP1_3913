package org.example;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


/**
 * Takes in a filename and returns each line of code one by one
 */
public class CodeScanner {
    ArrayList<String> lines;
    int index = 0;

    // stolen from https://stackoverflow.com/a/1740692
    String commentRegex = "//.*|(\"(?:\\\\[^\"]|\\\\\"|.)*?\")|(?s)/\\*.*?\\*/";

    private void Init(String filepath) {
        String content = null;
        try {
            content = Files.readString(Paths.get(filepath));
        } catch (java.io.IOException e) {
            System.out.println("Could not open file " + filepath);
            System.exit(1);
        }

        content = content.replaceAll(commentRegex, " ");
        String[] temp_lines = content.split("\n");

        lines = new ArrayList<>();

        for (String tempLine : temp_lines) {
            String line = tempLine.strip();
            if (!line.isEmpty()) lines.add(line);
        }
    }

    public CodeScanner(File file) {
        Init(file.getPath());
    }

    public CodeScanner(String filepath) {
        Init(filepath);
    }

    /**
     * Returns the next line in the file, null if the line does not exist
     *
     * @return The string of the next line in the file
     */
    public String nextLine() {
        if (index >= lines.size())
            return null;

        String line = lines.get(index);
        index++;
        return line;
    }
}
