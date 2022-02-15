package com.smola.words;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class WordsProbabilitiesTest {
    private WordsProbabilities underTest;
    private Words testWords;

    @BeforeEach
    void setUp(){
        underTest = new WordsProbabilities();
        testWords = new Words();
    }

    @Test
    void canMakeUniformDistribution(){
        // Given
        List<String> inputWords = new ArrayList<>();
        inputWords.add("hello");
        inputWords.add("goods");
        testWords.setPossibleGuesses(inputWords);

        // When
        LinkedHashMap<String, Double> actual =
                underTest.generateUniformDistribution(testWords).
                getProbabilityDistribution();

        // Then
        LinkedHashMap<String, Double> expected = new LinkedHashMap<>();
        expected.put("hello", 0.5);
        expected.put("goods", 0.5);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void canComputeMatchProbability(){
        // Given
        List<String> inputWords = new ArrayList<>();
        inputWords.add("hello");
        inputWords.add("goods");
        inputWords.add("birds");
        testWords.setPossibleGuesses(inputWords);
        LinkedHashMap<String, Double> inputProbabilities = new LinkedHashMap<>();
        inputProbabilities.put("hello", 0.1);
        inputProbabilities.put("goods", 0.1);
        inputProbabilities.put("birds", 0.8);
        testWords.setProbabilityDistribution(inputProbabilities);
        WordsPattern patternMatcher = new WordsPattern();
        LinkedHashMap<String, Integer> testPattern = new LinkedHashMap<>();
        testPattern.put("b", 0);
        testPattern.put("i", 0);
        testPattern.put("r", 0);
        testPattern.put("d", 0);
        testPattern.put("s", 0);
        testPattern.put("d3", 1);
        testPattern.put("s4", 1);
        // When
        double actual = underTest.computePatternProbability("birds",
                testWords,
                patternMatcher,
                testPattern);
        // Then
        double expected = 0.1;
        assertThat(actual).isEqualTo(expected);

    }
}
