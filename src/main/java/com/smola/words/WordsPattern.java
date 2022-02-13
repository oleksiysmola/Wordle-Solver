package com.smola.words;

import java.util.LinkedHashMap;

public class WordsPattern {
    public LinkedHashMap<String, Integer> generatePattern(
            String word, String fakeAnswer){
        // Establish map to fill results
        LinkedHashMap<String, Integer> pattern = new LinkedHashMap<>();
        // Loop over word
        for (int i = 0; i < word.length(); i++){
            // If character at position matches set map value to 2
            if (word.charAt(i) == fakeAnswer.charAt(i)){
                pattern.put(String.valueOf(word.charAt(i)) + i, 2);
            }
            // Value of 1 for characters that match but not in same position
            else if (fakeAnswer.contains(String.valueOf(word.charAt(i)))){
                pattern.put(String.valueOf(word.charAt(i)) + i, 1);
            }
            // Value of 0 for characters not included
            else {
                pattern.put(String.valueOf(word.charAt(i)) + i, 0);
            }
        }
        return pattern;
    }

    public boolean checkMatch(String word,
                              LinkedHashMap<String, Integer> pattern){
        // Initialise boolean to track result
        boolean result = true;
        // Counter for loop
        int n = 0;
        // Loop over pattern
        for (String key: pattern.keySet()){
            switch (pattern.get(key)){
                case 0:
                    // If word contains letter from pattern return false
                    if (word.contains(String.valueOf(key.charAt(0)))) {
                        return false;
                    }
                    break;
                case 1:
                    // If word doesn't include letter return false
                    if (!word.contains(String.valueOf(key.charAt(0)))){
                        return false;
                    }
                    break;
                case 2:
                    // If letter not at current position return false
                    if (word.charAt(n) != key.charAt(0)){
                        return false;
                    }
            }
            n++; // Update n
        }
        return result;
    }
}
