package com.smola.words;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class WordsFilesTest {
    private WordsFiles underTest;

    @BeforeEach
    void setUp(){
        underTest = new WordsFiles();
    }

    @Test
    void testCanReadWordsFromFile(){
        // Given
        File testFile = new File("wordsfilestest.txt");

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
    @Test
    void testCanPrintEntropyDistributionToFile(){
        // Given
        File testFile = new File("testprintentropy.txt");
        Words testWords = new Words();
        List<String> inputWords = List.of("force", "jumps");
        LinkedHashMap<String, Double> inputEntropy = new LinkedHashMap<>();
        inputEntropy.put(inputWords.get(0), 1.0);
        inputEntropy.put(inputWords.get(1), 1.0);
        testWords.setPossibleGuesses(inputWords);
        testWords.setEntropyDistribution(inputEntropy);
        // When
        underTest.printWordsEntropyToFile(testFile, testWords);
        // Then
        try {
            Scanner testScanner = new Scanner(testFile);
            String expected = "force,1.0";
            String actual = testScanner.nextLine();
            assertThat(actual).isEqualTo(expected);
            expected = "jumps,1.0";
            actual = testScanner.nextLine();
            assertThat(actual).isEqualTo(expected);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
