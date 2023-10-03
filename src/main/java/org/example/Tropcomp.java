package org.example;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

public class Tropcomp {

    ArrayList<File> listFiles = new ArrayList<>();

    public void getAllTestFiles(File dir){
        if (dir == null || !dir.isDirectory()) {
            return; // Base case: If it's not a directory, return
        }

        String targetDir = "test\\java";
        File pathToTarget = new File(dir, targetDir);


    }



    public static void main(String[] args) {



    }
}
