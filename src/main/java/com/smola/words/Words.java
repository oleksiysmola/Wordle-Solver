package com.smola.words;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public class Words {
    private List<String> possibleGuesses;
    private LinkedHashMap<String, Double> probabilityDistribution;
    private LinkedHashMap<String, Double> entropyDistribution;

    public Words(List<String> possibleGuesses,
                 LinkedHashMap<String, Double> probabilityDistribution,
                 LinkedHashMap<String, Double> entropyDistribution) {
        this.possibleGuesses = possibleGuesses;
        this.probabilityDistribution = probabilityDistribution;
        this.entropyDistribution = entropyDistribution;
    }

    public Words(){

    }

    public List<String> getPossibleGuesses() {
        return possibleGuesses;
    }

    public void setPossibleGuesses(List<String> possibleGuesses) {
        this.possibleGuesses = possibleGuesses;
    }

    public LinkedHashMap<String, Double> getProbabilityDistribution() {
        return probabilityDistribution;
    }

    public void setProbabilityDistribution(LinkedHashMap<String, Double> probabilityDistribution) {
        this.probabilityDistribution = probabilityDistribution;
    }

    public LinkedHashMap<String, Double> getEntropyDistribution() {
        return entropyDistribution;
    }

    public void setEntropyDistribution(LinkedHashMap<String, Double> entropyDistribution) {
        this.entropyDistribution = entropyDistribution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Words words = (Words) o;
        return Objects.equals(possibleGuesses, words.possibleGuesses) && Objects.equals(probabilityDistribution, words.probabilityDistribution) && Objects.equals(entropyDistribution, words.entropyDistribution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(possibleGuesses, probabilityDistribution, entropyDistribution);
    }

    @Override
    public String toString() {
        return "Words{" +
                "possibleGuesses=" + possibleGuesses +
                ", probabilityDistribution=" + probabilityDistribution +
                ", entropyDistribution=" + entropyDistribution +
                '}';
    }
}