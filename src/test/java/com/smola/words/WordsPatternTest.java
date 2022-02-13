package com.smola.words;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class WordsPatternTest {
    private WordsPattern underTest;

    @BeforeEach
    void setUp(){
        underTest = new WordsPattern();
    }

    @Test
    void generatePattern(){
        // Given
        String testWord = "birds";
        char[] testCharacters = testWord.toCharArray();
        String testWordForPattern = "cries";
        // When
        LinkedHashMap<Character, Integer> actual = underTest.
                generatePattern(testWord, testWordForPattern);
        // Then
        LinkedHashMap<Character, Integer> expected = new LinkedHashMap<>();
        expected.put(testCharacters[0], 0);
        expected.put(testCharacters[1], 1);
        expected.put(testCharacters[2], 1);
        expected.put(testCharacters[3], 0);
        expected.put(testCharacters[4], 2);
        assertThat(actual).isEqualTo(expected);
    }
}
