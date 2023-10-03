package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tassert {

    private int compteur;

    public Tassert(){
        this.compteur = 0;
    }

    public void countTassert(BufferedReader r) throws IOException {
        String line;
        while((line = r.readLine()) != null){
            if((line.contains("assert") || line.contains("fail")) && !line.contains("//") && !line.contains("import")){
                this.compteur++;
            }
        }
    }

    public int getCompteur(){
        return this.compteur;
    }


    public static void main(String args[]) throws FileNotFoundException {

        int result = 0;
        if (args.length != 1) {
            System.out.println("Must be one and only one input file");
            System.exit(1);
        }
        try {
            String file = args[0];
            Tassert tassert = new Tassert();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            tassert.countTassert(reader);
            result = tassert.getCompteur();
        } catch (IOException ex) {
            System.out.println("Erreur de lecture");
            System.exit(1);
        }

        System.out.println(result);
    }

}
