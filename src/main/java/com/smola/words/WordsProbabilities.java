package com.smola.words;

import java.util.LinkedHashMap;
import java.util.List;

public class WordsProbabilities {
    // Method for generating a uniform probability distribution
    public Words generateUniformDistribution(Words words){
        // Initialise map for distribution
        LinkedHashMap<String, Double> distribution = new LinkedHashMap<>();
        // Obtain pool of words along with total number of words
        List<String> wordPool = words.getPossibleGuesses();
        Double numberOfWords = (double) wordPool.size();
        // Loop over the words
        for (String word: wordPool){
            distribution.put(word, 1/numberOfWords);
        }
        // Sets distribution of words object to be uniform
        words.setProbabilityDistribution(distribution);
        return words;
    }
    // Method for calculating probability of returning a certain pattern
    public double computePatternProbability(Words words,
                                            WordsPattern patternChecker,
                                            LinkedHashMap<String, Integer> pattern){
        // Get words from list of possible guesses
        List<String> wordsList = words.getPossibleGuesses();
        LinkedHashMap<String, Double> wordProbabilities = words.getProbabilityDistribution();
        // Initialise variable to store result
        double result = 0;
        // Loop over the words
        for (int i = 0; i < wordsList.size(); i++){
            // If word is a possible match add the probability to the total
            if (patternChecker.checkMatch(wordsList.get(i), pattern)){
                result += wordProbabilities.get(wordsList.get(i));
            }
        }
        return result;
    }
}
