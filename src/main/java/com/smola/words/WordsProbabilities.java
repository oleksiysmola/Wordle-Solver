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
}
