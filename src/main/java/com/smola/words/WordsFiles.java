package com.smola.words;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
    // Method to read words and entropy distribution from given text file
    public Words readWordsEntropyFromFile(File file, Words words){
        // Initialise empty string list
        List<String> wordList = new ArrayList<>();
        // Initialise empty map for entropy
        LinkedHashMap<String, Double> entropies = new LinkedHashMap<>();
        try {
            // Initialise scanner
            Scanner scanner = new Scanner(file);
            // Initialise line and split
            String line;
            String[] lineSplit;
            // Loop over all lines in file
            while (scanner.hasNext()){
                line = scanner.nextLine();
                lineSplit = line.split(",");
                // Fill words and entropy distribution
                wordList.add(lineSplit[0]);
                entropies.put(lineSplit[0], Double.valueOf(lineSplit[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Fill words object
        words.setPossibleGuesses(wordList);
        words.setEntropyDistribution(entropies);
        return words;
    }
    public void printWordsEntropyToFile(File file, Words words){
        // Obtain words from list
        List<String> wordList = words.getPossibleGuesses();
        LinkedHashMap<String, Double> entropyDistribution = words.getEntropyDistribution();
        try {
            // Create file
            file.createNewFile();
            // Initiate file writer and print writer
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            // Line to write
            String line;
            // Loop over word list
            for (String word: wordList){
                line = word + "," + entropyDistribution.get(word);
                printWriter.println(line);
            }
            // Conclude writing
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
