package com.smola.words;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordsFiles {
    // Method to read words from given text file
    public List<String> readWordsFromFile(File file){
        // Initialise empty string list
        List<String> words = new ArrayList<>();
        try {
            // Initialise scanner
            Scanner scanner = new Scanner(file);
            // Loop over all lines in file
            while (scanner.hasNext()){
                words.add(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }
}
