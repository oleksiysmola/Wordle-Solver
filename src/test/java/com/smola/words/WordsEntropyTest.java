package com.smola.words;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
}
