package org.example;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

public class Tropcomp {

    ArrayList<File> listFiles = new ArrayList<>();

    public void getAllTestFiles(File dir){
        if (dir == null || !dir.isDirectory()) {
            System.out.println("Not a directory or null");
            return; // Base case: If it's not a directory, return
        }

        String targetDir = "test\\java";
        File pathToTarget = new File(dir, targetDir);
        //System.out.println(pathToTarget.getPath());
        Tls tls = new Tls();

    }



    public static void main(String[] args) {

        Pair<File, File> parsed_args = Utils.ParseArgs(args);
        File input_file = parsed_args.first;

        Tropcomp tc = new Tropcomp();
        tc.getAllTestFiles(input_file);

    }
}
