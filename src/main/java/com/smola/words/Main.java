package com.smola.words;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        // Create instance of WordsFiles class
        WordsFiles wordsFiles = new WordsFiles();
        // Generate new words object
        Words initialWords = new Words();
        // Get initial words
        List<String> initialWordsList = new ArrayList<>();
        File initialGuessFile = new File("initialentropy.txt");
        initialWords = wordsFiles.readWordsEntropyFromFile(initialGuessFile, initialWords);
        initialWords.setPossibleGuesses(initialWordsList);
        // Set to uniform distribution
        WordsProbabilities wordsProbabilities = new WordsProbabilities();
        wordsProbabilities.generateUniformDistribution(initialWords);
        // Get entropy distribution
        WordsEntropy wordsEntropy = new WordsEntropy();
        WordsPattern wordsPattern = new WordsPattern();
    }
}
