package org.example;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;


/**
 * Takes in a config from the command line arguments and provides a layer of abstraction
 * for input and output. For example the programmer only has to do io.Output("Hello World")
 * and the result is in a file, stdout, etc... depending on the command line arguments.
 * This class is also responsible for parsing the command line arguments and making sure
 * they are valid.
 */
public class InputOutput {
    File input_file;
    PrintStream output_stream;

    public InputOutput(String[] args) {
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
        input_file = new File(input_filepath);
        if (!input_file.exists()) {
            System.out.println("The input file does not exist");
            System.exit(1);
        }

        // maybe the second argument was not passed
        String output_filepath = cmd.getOptionValue("output");

        // attempt to open the output file if provided
        if (output_filepath == null) {
            output_stream = System.out;
        } else {
            try {
                output_stream = new PrintStream(output_filepath, StandardCharsets.UTF_8);
            } catch (IOException e) {
                System.out.println("Could not open output file");
                System.exit(1);
            }
        }
    }

    /**
     * Prints the object to the output stream
     * Works for all data types
     *
     * @param object The object to print
     */
    public void Output(Object object) {
        output_stream.println(object);
    }
}
