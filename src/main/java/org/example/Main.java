package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.commons.cli.*;

public class Main {
    private static Pair<String, String> ParseArgs(String[] args) {
        Options options = new Options();

        Option output = new Option("o", "output", true, "output file");
        output.setRequired(false);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp(args[0], options);

            System.exit(1);
        }

        String output_string = cmd.getOptionValue("output");
        String input_string = cmd.getArgs()[0];
        return new Pair<>(input_string, output_string);
    }

    public static int GetTloc(Scanner reader) {
        int tloc = 0;

        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            data = data.strip();

            // whitespace
            if (data.isEmpty()) continue;
            // single line comment
            if (data.startsWith("//")) continue;
            // start block comment
            if (data.startsWith("/*")) continue;
            // block comment
            if (data.startsWith("*")) continue;
            // end block comment
            if (data.startsWith("*/")) continue;

            tloc += 1;
        }

        return tloc;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Pair<String, String> parsed_args = ParseArgs(args);
        String input_file_path = parsed_args.first;
        System.out.println("input_file_path = " + input_file_path);
        String output_file_path = parsed_args.second;
        System.out.println("output_file_path = " + output_file_path);

        File input_file = new File(input_file_path);
        Scanner reader = new Scanner(input_file);

        int tloc = GetTloc(reader);
        System.out.println("tloc = " + tloc);

        reader.close();
    }
}