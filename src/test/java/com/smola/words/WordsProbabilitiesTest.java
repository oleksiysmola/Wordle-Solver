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

}
