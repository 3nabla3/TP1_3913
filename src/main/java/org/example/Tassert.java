package org.example;

public class Tassert {
    public static int CountAssert(String filepath) {
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
        Pair<String, String> parsed_args = Utils.ParseArgs(args);
        String input_filename = parsed_args.first;

        int tassert = CountAssert(input_filename);
        System.out.println(tassert);
    }
}
