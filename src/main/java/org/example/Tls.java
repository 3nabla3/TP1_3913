package org.example;

import java.io.File;
import java.util.ArrayList;

public class Tls {
    // Inspired from GPT prompt
    private static void Impl_ListAllFilesRec(File directory, ArrayList<File> fileList) {
        if (directory == null || !directory.isDirectory())
            return; // Base case: If it's not a directory, return

        File[] files = directory.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isFile()) {
                // If it's a java file, add its path to the list
                String path = file.getPath();
                if (path.endsWith(".java"))
                    fileList.add(file);

            } else if (file.isDirectory()) {
                // If it's a directory, recurse into it
                Impl_ListAllFilesRec(file, fileList);
            }
        }
    }

    /**
     * Takes in a directory and returns an arraylist of all files in that directory
     *
     * @param dir The directory to search
     * @return An arraylist of all files in that directory
     */
    public static ArrayList<File> ListAllFiles(File dir) {
        // TODO: make this an iterator for efficiency
        ArrayList<File> fileList = new ArrayList<>();
        Impl_ListAllFilesRec(dir, fileList);

        return fileList;
    }

    /**
     * Returns the package name of a file
     *
     * @param file The file to get the package name of
     * @return The package name of the file
     */
    public static String GetPackageName(File file) {
        ArrayList<String> dirs = new ArrayList<>();

        File parent = file.getParentFile();
        while (parent != null && !parent.getName().equals("java")) {
            // add the directory name to the front of the list, so we
            // don't have to iterate over the list backwards, which looks ugly :(
            dirs.add(0, parent.getName());
            parent = parent.getParentFile();
        }

        return String.join(".", dirs);
    }


    public static void main(String[] args) {
        InputOutput io = new InputOutput(args);
        ArrayList<File> file_list = ListAllFiles(io.input_file);

        for (File file : file_list) {
            TlsOutput tlsOutput = new TlsOutput(file);
            if (tlsOutput.ContainsTest())
                io.Output(tlsOutput);
        }
    }
}
