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
    void testGeneratePattern(){
        // Given
        String testWord = "birds";
        String testWordForPattern = "cries";
        // When
        LinkedHashMap<String, Integer> actual = underTest.
                generatePattern(testWord, testWordForPattern);
        // Then
        LinkedHashMap<String, Integer> expected = new LinkedHashMap<>();
        expected.put("b" + 0, 0);
        expected.put("i" + 1, 1);
        expected.put("r" + 2, 1);
        expected.put("d" + 3, 0);
        expected.put("s" + 4, 2);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testCheckPatternReturnsTrue(){
        // Given
        String testWord = "solid";
        LinkedHashMap<String, Integer> testPattern = new LinkedHashMap<>();
        testPattern.put("c", 0);
        testPattern.put("m", 0);
        testPattern.put("l", 2);
        testPattern.put("d", 1);
        testPattern.put("o", 1);
        // When
        boolean actual = underTest.checkMatch(testWord, testPattern);
        // Then
        assertThat(actual).isTrue();
    }
    @Test
    void testCheckPatternReturnsFalse(){
        // Given
        String testWord = "solid";
        LinkedHashMap<String, Integer> testPattern = new LinkedHashMap<>();
        testPattern.put("c", 0);
        testPattern.put("m", 0);
        testPattern.put("l", 2);
        testPattern.put("d", 1);
        testPattern.put("o", 0);
        // When
        boolean actual = underTest.checkMatch(testWord, testPattern);
        // Then
        assertThat(actual).isFalse();
    }
}