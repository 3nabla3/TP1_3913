package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.ListIterator;

public class Tls {
    // GPT prompt
    private static void Impl_ListAllFilesRec(File directory, ArrayList<File> fileList) {
        if (directory == null || !directory.isDirectory()) {
            return; // Base case: If it's not a directory, return
        }

        File[] files = directory.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isFile()) {
                // If it's a file, add its path to the list
                String path = file.getPath();
                if (path.endsWith(".java"))
                    fileList.add(file);

            } else if (file.isDirectory()) {
                // If it's a directory, recurse into it
                Impl_ListAllFilesRec(file, fileList);
            }
        }
    }

    public static ArrayList<File> ListAllFiles(File dir) {
        ArrayList<File> fileList = new ArrayList<>();
        Impl_ListAllFilesRec(dir, fileList);

        return fileList;
    }

    public static String GetPackageName(File file) {
        ArrayList<String> dirs = new ArrayList<>();

        File parent = file.getParentFile();
        while (parent != null && !parent.getName().equals("java")) {
            dirs.add(parent.getName());
            parent = parent.getParentFile();
        }

        StringBuilder package_name = new StringBuilder();
        ListIterator<String> li = dirs.listIterator(dirs.size());

        while (li.hasPrevious()) {
            if (package_name.length() != 0)
                package_name.append('.');
            package_name.append(li.previous());
        }

        return package_name.toString();
    }


    public static void main(String[] args) {
        Pair<File, File> parsed_args = Utils.ParseArgs(args);
        File input_dir = parsed_args.first;

        ArrayList<File> file_list = ListAllFiles(input_dir);

        for (File file : file_list) {
            TlsOutput tlsOutput = new TlsOutput(file);
            System.out.println(tlsOutput);
        }
    }
}
