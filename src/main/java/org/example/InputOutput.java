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
    final File input_file;
    final int seuil;
    PrintStream output_stream;

    private static void printHelp(String program_name) {
        if (program_name.equals("Tropcomp"))
            System.out.printf("Usage: %s [-o <output_file>] <input_file> <seuil>\n", program_name);
        else
            System.out.printf("Usage: %s [-o <output_file>] <input_file>\n", program_name);
    }

    public InputOutput(String[] args, String program_name) {
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
            printHelp(program_name);
            System.exit(1);
        }

        // make sure the first argument is a valid file
        String input_filepath = cmd.getArgs()[0];
        input_file = new File(input_filepath);
        if (!input_file.exists()) {
            System.out.println("The input file does not exist");
            System.exit(1);
        }

        // maybe the seuil argument was not passed
        if (cmd.getArgs().length != 2)
            seuil = 0;
        else
            seuil = Integer.parseInt(cmd.getArgs()[1]);


        // maybe the output argument was not passed
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
