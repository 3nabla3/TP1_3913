package org.example;

import org.apache.commons.cli.*;

public class Utils {
    public static Pair<String, String> ParseArgs(String[] args) {
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
}
