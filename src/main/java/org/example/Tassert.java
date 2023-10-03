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
        Pair<File, File> parsed_args = Utils.ParseArgs(args);
        File input_file = parsed_args.first;

        int tassert = CountAssert(input_file);
        System.out.println(tassert);
    }
}
