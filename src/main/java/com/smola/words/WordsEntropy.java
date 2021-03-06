package com.smola.words;

import java.util.*;

import static java.lang.Math.log;

public class WordsEntropy {
    public double computeWordEntropy(String word,
                                     Words words,
                                     WordsPattern wordsPattern,
                                     WordsProbabilities wordsProbabilities){
        // Initialise list for patterns
        List<LinkedHashMap> patterns = new ArrayList<>();
        LinkedHashMap<String, Integer> newPattern = new LinkedHashMap<>();
        // Get word list and probability distribution
        List<String> wordList = words.getPossibleGuesses();
        LinkedHashMap<String, Double> probabilityDistribution = words.getProbabilityDistribution();
        // Start a reduced list that doesn't include guess word
        List<String> reducedWordsList = new ArrayList<>();
        for (int i = 0; i < wordList.size(); i++) {
            reducedWordsList.add(wordList.get(i));
        }
        reducedWordsList.remove(word); // Remove guess word as we would over count when matching
        Words reducedWords = new Words();
        reducedWords.setPossibleGuesses(reducedWordsList);
        reducedWords.setProbabilityDistribution(probabilityDistribution);

        // Loop over list of words
        for (int i = 0; i < reducedWordsList.size(); i++){
            newPattern = wordsPattern.generatePattern(word, reducedWordsList.get(i));
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
                    word,
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

    public Words obtainEntropyDistribution(Words words,
                                           WordsPattern wordsPattern,
                                           WordsProbabilities wordsProbabilities){
        // Get words list
        List<String> wordsList = words.getPossibleGuesses();
        // Initialise map for entropy distribution
        LinkedHashMap<String, Double> entropyDistribution = new LinkedHashMap<>();
        // Initialise entropy variable
        double entropy;
        // Loop over words
        for (int i = 0; i < wordsList.size(); i++){
            // Compute entropy for word then add to map
            entropy = computeWordEntropy(wordsList.get(i),
                    words,
                    wordsPattern,
                    wordsProbabilities);
            entropyDistribution.put(wordsList.get(i), entropy);
            System.out.println(wordsList.get(i) + ": " + entropy + " bits");
        }
        // Sets the entropy distribution in the words object
        words.setEntropyDistribution(entropyDistribution);
        return words;
    }

    public double log2(double value){
        return log(value)/log(2);
    }
}
