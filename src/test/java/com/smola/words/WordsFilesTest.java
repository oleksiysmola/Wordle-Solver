package com.smola.words;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WordsFilesTest {
    private WordsFiles underTest;
    @BeforeEach
    void setUp(){
        underTest = new WordsFiles();
    }

    @Test
    void testCanReadWordsFromFile(){
        // Given
        File testFile = new File("wordfilestest.txt");

        // When
        List<String> actual = underTest.readWordsFromFile(testFile);

        // Then
        List<String> expected = new ArrayList<>();
        expected.add("birds");
        expected.add("vices");
        expected.add("mouse");
        expected.add("boats");
        assertThat(actual).isEqualTo(expected);
    }
}
