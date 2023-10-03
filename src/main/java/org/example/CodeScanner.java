package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CodeScanner {
    ArrayList<String> lines;
    int index = 0;

    // stolen from https://stackoverflow.com/a/1740692
    String commentRegex = "//.*|(\"(?:\\\\[^\"]|\\\\\"|.)*?\")|(?s)/\\*.*?\\*/";

    public CodeScanner(String filepath) throws IOException {
        String content = Files.readString(Paths.get(filepath));
        content = content.replaceAll(commentRegex, " ");
        String[] temp_lines = content.split("\n");

        lines = new ArrayList<>();

        for (String tempLine : temp_lines) {
            String line = tempLine.strip();
            if (!line.isEmpty()) lines.add(line);
        }
    }

    public String nextLine() {
        if (index >= lines.size())
            return null;

        String line = lines.get(index);
        index++;
        return line;
    }
}
