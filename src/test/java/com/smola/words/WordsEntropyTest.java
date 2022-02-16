package com.smola.words;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WordsEntropyTest {
    private WordsEntropy underTest;

    @BeforeEach
    void setUp(){
        underTest = new WordsEntropy();
    }

    @Test
    void testLog2(){
        // Given
        double testInput = 8;
        // When
        double actual = underTest.log2(testInput);
        // Then
        double expected = 3;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testComputeWordEntropy(){
        // Given
        List<String> testWordsList = new ArrayList<>();
        testWordsList.add("birds");
        testWordsList.add("troll");
        testWordsList.add("anger");
        testWordsList.add("bonds");
        LinkedHashMap<String, Double> testProbabilities = new LinkedHashMap<>();
        for (int i = 0; i < testWordsList.size(); i++) {
            testProbabilities.put(testWordsList.get(i), 0.25);
        }
        Words testWords = new Words();
        testWords.setPossibleGuesses(testWordsList);
        testWords.setProbabilityDistribution(testProbabilities);
        WordsPattern wordsPattern = new WordsPattern();
        WordsProbabilities wordsProbabilities = new WordsProbabilities();
        // When
        double actual = underTest.computeWordEntropy("birds",
                testWords, wordsPattern, wordsProbabilities);
        // Then
        double expected = 1.5;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void canGenerateEntropyDistribution(){
        // Given
        List<String> testWordsList = new ArrayList<>();
        testWordsList.add("birds");
        testWordsList.add("troll");
        testWordsList.add("anger");
        testWordsList.add("bonds");
        LinkedHashMap<String, Double> testProbabilities = new LinkedHashMap<>();
        for (int i = 0; i < testWordsList.size(); i++) {
            testProbabilities.put(testWordsList.get(i), 0.25);
        }
        Words testWords = new Words();
        testWords.setPossibleGuesses(testWordsList);
        testWords.setProbabilityDistribution(testProbabilities);
        WordsPattern wordsPattern = new WordsPattern();
        WordsProbabilities wordsProbabilities = new WordsProbabilities();
        // When
        Words actual = underTest.obtainEntropyDistribution(testWords,
                wordsPattern, wordsProbabilities);
        // Then
        LinkedHashMap<String, Double> expected = new LinkedHashMap<>();
        expected.put("birds", 1.5);
        expected.put("troll", 1.5);
        expected.put("anger", 1.5);
        expected.put("bonds", 2.0);
        assertThat(actual.getEntropyDistribution()).isEqualTo(expected);
    }
}
