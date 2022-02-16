package com.smola.words;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class WordsPattern {
    public LinkedHashMap<String, Integer> generatePattern(
            String word, String fakeAnswer){
        // Establish map to fill results
        LinkedHashMap<String, Integer> pattern = new LinkedHashMap<>();
        // Loop over word
        for (int i = 0; i < word.length(); i++) {
            // If key does not exist yet set to zero and count number of occurrences in target
            if (!pattern.containsKey(String.valueOf(word.charAt(i)))) {
                pattern.put(String.valueOf(word.charAt(i)), 0);
                for (int j = 0; j < fakeAnswer.length(); j++) {
                    if (word.charAt(i) == fakeAnswer.charAt(j)) {
                        pattern.put(String.valueOf(word.charAt(i)),
                            pattern.get(String.valueOf(word.charAt(i))) + 1);
                    }
                }
            }
        }
        // Loop again for letters in same position
        for (int i = 0; i < word.length(); i++){
            // If character at position matches set map value to 1
            if (word.charAt(i) == fakeAnswer.charAt(i)) {
                pattern.put(String.valueOf(word.charAt(i)) + i, 1);
                // Deduct from occurrences since we treat letters at identical positions separately
                pattern.put(String.valueOf(word.charAt(i)),
                        pattern.get(String.valueOf(word.charAt(i))) - 1);
            }
        }
        return pattern;
    }
    // Method to check for matches
    public boolean checkMatch(String word, String targetWord,
                              LinkedHashMap<String, Integer> pattern){
        // Map for new pattern
        LinkedHashMap<String, Integer> newPattern = new LinkedHashMap<>();
        newPattern = generatePattern(word, targetWord);
        // Checks if the patterns match
        boolean result = pattern.equals(newPattern);
        return result;
    }
    // Method to filter words that match
    public Words findMatchingWords(String guess, Words words,
                                   LinkedHashMap<String, Integer> pattern){
        // Get current words list and start empty list to track next set of guesses
        List<String> wordList = words.getPossibleGuesses();
        List<String> newGuesses = new ArrayList<>();
        // Initialise variable to determine matches
        boolean match;
        // Loop over words
        for (int i = 0; i < wordList.size(); i++){
            // Find words that match the guess word and add them to the list
            match = checkMatch(guess, wordList.get(i), pattern);
            if (match && !wordList.get(i).equals(guess)) {
                newGuesses.add(wordList.get(i));
            }
        }
        // Set new guesses to the matches
        words.setPossibleGuesses(newGuesses);
        return words;
    }
}
