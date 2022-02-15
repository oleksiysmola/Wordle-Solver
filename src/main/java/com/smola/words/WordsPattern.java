package com.smola.words;

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

    public boolean checkMatch(String word,
                              LinkedHashMap<String, Integer> pattern){
        // Initialise maps for tracking number of occurrences
        LinkedHashMap<Character, Integer> occurrencesPattern = new LinkedHashMap<>();
        LinkedHashMap<Character, Integer> occurrencesTarget = new LinkedHashMap<>();
        // Counter for loop
        int n = 0;
        for (String key: pattern.keySet()){
            if (occurrencesPattern.get(key.charAt(0)).equals(null)){
                occurrencesPattern.put(key.charAt(0), 0);
            }
            if (occurrencesTarget.get(word.charAt(n)).equals(null)){
                occurrencesTarget.put(word.charAt(n), 0);
            } else {
                occurrencesTarget.put(word.charAt(n),
                        occurrencesTarget.get(word.charAt(n)) + 1);
            }
            n++; // Update count
        }
        // Initialise boolean to track result
        boolean result = true;
        // Reset counter for loop
        n = 0;
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
                    } else if (occurrencesPattern.get(key.charAt(0))
                            <= occurrencesPattern.get(word))
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
