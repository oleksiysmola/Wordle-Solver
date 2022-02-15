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
    void testCheckPatternReturnsTrue(){
        // Given
        String testWord = "broom";
        String testTargetWord = "booms";
        LinkedHashMap<String, Integer> testPattern = new LinkedHashMap<>();
        testPattern.put("b0", 1);
        testPattern.put("b", 0);
        testPattern.put("r", 0);
        testPattern.put("o2", 1);
        testPattern.put("o", 1);
        testPattern.put("m", 1);
        // When
        boolean actual = underTest.checkMatch(testWord, testTargetWord, testPattern);
        // Then
        assertThat(actual).isTrue();
    }
    @Test
    void testCheckPatternReturnsFalse(){
        // Given
        String testWord = "broom";
        String testTargetWord = "bonds";
        LinkedHashMap<String, Integer> testPattern = new LinkedHashMap<>();
        testPattern.put("b0", 1);
        testPattern.put("b", 0);
        testPattern.put("r", 0);
        testPattern.put("o2", 1);
        testPattern.put("o", 1);
        testPattern.put("m", 1);
        // When
        boolean actual = underTest.checkMatch(testWord, testTargetWord, testPattern);
        // Then
        assertThat(actual).isFalse();
    }
}
