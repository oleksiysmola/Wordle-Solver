package com.smola.words;

import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args){
        // Create instance of WordsFiles class
        WordsFiles wordsFiles = new WordsFiles();
        // Generate new words object
        Words words = new Words();
        // Get initial words
        File initialGuessFile = new File("initialentropy.txt");
        words = wordsFiles.readWordsEntropyFromFile(initialGuessFile, words);
        // Set to uniform distribution
        WordsProbabilities wordsProbabilities = new WordsProbabilities();
        wordsProbabilities.generateUniformDistribution(words);
        // Get entropy distribution
        WordsEntropy wordsEntropy = new WordsEntropy();
        WordsPattern wordsPattern = new WordsPattern();

        // CLI
        System.out.println("Welcome to Wordle Solver Version 1!");
        System.out.println("------------------------------------");
        System.out.println("Would you like to play a game? Y/N");
        // Start up scanner
        Scanner scanner = new Scanner(System.in);
        String userResponse;
        // List and map for current guess pool
        List<String> currentGuesses;
        LinkedHashMap<String, Double> currentEntropy;
        // Store best guess and entropy
        Double highestEntropy = 0.0;
        String bestGuess = "";
        // Store word guessed
        String wordGuessed;
        // For pattern input
        String patternInput;
        String[] patternSplit;
        String[] patternKeyValuePairs;
        LinkedHashMap<String, Integer> processedPattern;
        // Boolean to track if game is running
        boolean isRunning = true;
        // Loop while running
        while (isRunning){
            userResponse = scanner.nextLine();
            switch (userResponse){
                case "Y":
                    // Obtain current word and entropy pool
                    currentGuesses = words.getPossibleGuesses();
                    currentEntropy = words.getEntropyDistribution();
                    System.out.println("Computing best guess...");
                    // Reset highest entropy
                    highestEntropy = 0.0;
                    // Loop through all available guess
                    for (String word: currentGuesses){
                        // Compares to current highest entropy
                        if (currentEntropy.get(word) >= highestEntropy){
                            bestGuess = word;
                            highestEntropy = currentEntropy.get(word);
                        }
                    }
                    System.out.println("Your best guess would be " + bestGuess
                    + " which maximises entropy at " + highestEntropy + " bits!");
                    System.out.println(" ");
                    // If we win time to wrap up
                    System.out.println("Did the guess succeed? Y/N");
                    userResponse = scanner.nextLine();
                    if (userResponse.equals("Y")){
                        System.out.println("Congratulations!");
                        isRunning = false;
                        break;
                    }
                    System.out.println("Please enter your guess:");
                    wordGuessed = scanner.nextLine();
                    System.out.println("Please enter the pattern you obtained:");
                    patternInput = scanner.nextLine();
                    // Processes input pattern
                    patternSplit = patternInput.split(" ");
                    processedPattern = new LinkedHashMap<>();
                    for (int i = 0; i < patternSplit.length; i++){
                        patternKeyValuePairs = patternSplit[i].split(",");
                        processedPattern.put(patternKeyValuePairs[0],
                                Integer.valueOf(patternKeyValuePairs[1]));
                    }
                    System.out.println("Reducing words list...");
                    // Filter words that do not match and recompute probabilities and entropy
                    words = wordsPattern.findMatchingWords(wordGuessed, words, processedPattern);
                    words = wordsProbabilities.generateUniformDistribution(words);
                    words = wordsEntropy.obtainEntropyDistribution(words, wordsPattern, wordsProbabilities);
                    System.out.println("Do you wish to continue playing? Y/N");
                    break;
                case "N":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Sorry, please enter Y/N");
            }
        }
    }
}
