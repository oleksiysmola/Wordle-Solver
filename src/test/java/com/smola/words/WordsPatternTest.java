package com.smola.words;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
        expected.put("b", 0);
        expected.put("i", 1);
        expected.put("r", 1);
        expected.put("d", 0);
        expected.put("s", 0);
        expected.put("s" + 4, 1);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @Disabled
    void testCheckPatternReturnsTrue(){
        // Given
        String testWord = "solid";
        LinkedHashMap<String, Integer> testPattern = new LinkedHashMap<>();
        testPattern.put("c" + 0, 0);
        testPattern.put("m" + 1, 0);
        testPattern.put("l" + 2, 2);
        testPattern.put("d" + 3, 1);
        testPattern.put("o" + 4, 1);
        // When
        boolean actual = underTest.checkMatch(testWord, testPattern);
        // Then
        assertThat(actual).isTrue();
    }
    @Test
    @Disabled
    void testCheckPatternReturnsFalse(){
        // Given
        String testWord = "solid";
        LinkedHashMap<String, Integer> testPattern = new LinkedHashMap<>();
        testPattern.put("c" + 0, 0);
        testPattern.put("m" + 1, 0);
        testPattern.put("l" + 2, 2);
        testPattern.put("d" + 3, 1);
        testPattern.put("o" + 4, 0);
        // When
        boolean actual = underTest.checkMatch(testWord, testPattern);
        // Then
        assertThat(actual).isFalse();
    }
}
