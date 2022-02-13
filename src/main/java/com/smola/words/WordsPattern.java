package com.smola.words;

import java.util.LinkedHashMap;

public class WordsPattern {
    public LinkedHashMap<Character, Integer> generatePattern(
            String word, String fakeAnswer){
        // Establish map to fill results
        LinkedHashMap<Character, Integer> pattern = new LinkedHashMap<>();
        // Loop over word
        for (int i = 0; i < word.length(); i++){
            // If character at position matches set map value to 2
            if (word.charAt(i) == fakeAnswer.charAt(i)){
                pattern.put(word.charAt(i), 2);
            }
            // Value of 1 for characters that match but not in same position
            else if (fakeAnswer.contains(String.valueOf(word.charAt(i)))){
                pattern.put(word.charAt(i), 1);
            }
            // Value of 0 for characters not included
            else {
                pattern.put(word.charAt(i), 0);
            }
        }
        return pattern;
    }
}
