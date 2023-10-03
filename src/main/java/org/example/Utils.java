package org.example;

import org.apache.commons.cli.*;

import java.io.File;

public class Utils {
    public static Pair<File, File> ParseArgs(String[] args) {
        Options options = new Options();

        Option output = new Option("o", "output", true, "output file");
        output.setRequired(false);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        // attempt to parse the arguments
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp(args[0], options);
            System.exit(1);
        }
        if (cmd.getArgs().length == 0) {
            System.out.println("Usage: program -o <output_file> <input_file>");
            System.exit(1);
        }

        // make sure the first argument is a valid file
        String input_filepath = cmd.getArgs()[0];
        File input_file = new File(input_filepath);
        if (!input_file.exists()) {
            System.out.println("The input file does not exist");
            System.exit(1);
        }

        // maybe the second argument was not passed
        String output_filepath = cmd.getOptionValue("output");
        File output_file;
        if (output_filepath == null)
            output_file = null;
        else
            output_file = new File(output_filepath);

        return new Pair<>(input_file, output_file);
    }
}
