package com.smola.words;

import java.util.*;

import static java.lang.Math.log;

public class WordsEntropy {
    public double computeWordEntropy(String word, Words words){
        // Invoke word probability class
        WordsProbabilities wordsProbabilities = new WordsProbabilities();
        // Initialise list for patterns and generate instance of pattern class
        List<LinkedHashMap> patterns = new ArrayList<>();
        LinkedHashMap<String, Integer> newPattern = new LinkedHashMap<>();
        WordsPattern wordsPattern = new WordsPattern();
        // Get word list and probability distribution
        List<String> wordList = words.getPossibleGuesses();
        LinkedHashMap<String, Double> probabilityDistribution = words.getProbabilityDistribution();
        wordList.remove(word); // We are not matching the word with itself!
        Words reducedWords = new Words();
        reducedWords.setPossibleGuesses(wordList);
        reducedWords.setProbabilityDistribution(probabilityDistribution);
        // Loop over list of words
        for (int i = 0; i < wordList.size(); i++){
            newPattern = wordsPattern.generatePattern(word, wordList.get(i));
            if (!patterns.contains(newPattern)){
                patterns.add(newPattern);
            }
        }
        // Initialise variables for loop
        double probability;
        double entropy = 0;
        // Loop over patterns and words
        for (int i = 0; i < patterns.size(); i++){
            probability = wordsProbabilities.computePatternProbability(
                    reducedWords,
                    wordsPattern,
                    patterns.get(i));
            entropy -= probability*log2(probability);
        }
        // Case where word itself is the answer!
        probability = probabilityDistribution.get(word);
        entropy -= probability*log2(probability);
        return entropy;
    }

    public Words obtainEntropyDistribution(Words words){
        // Get words list
        List<String> wordsList = words.getPossibleGuesses();
        // Initialise map for entropy distribution
        LinkedHashMap<String, Double> entropyDistribution = new LinkedHashMap<>();
        // Initialise entropy variable
        double entropy;
        // Loop over words
        for (int i = 0; i < wordsList.size(); i++){
            // Compute entropy for word then add to map
            entropy = computeWordEntropy(wordsList.get(i), words);
            entropyDistribution.put(wordsList.get(i), entropy);
        }
        // Sets the entropy distribution in the words object
        words.setEntropyDistribution(entropyDistribution);
        return words;
    }

    public double log2(double value){
        return log(value)/log(2);
    }
}
