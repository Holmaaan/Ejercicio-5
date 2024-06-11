package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TextAnalyzer {
    private String filePath;
    private String content;
    private List<String> words;
    private List<String> sentences;

    public TextAnalyzer(String filePath) throws IOException {
        this.filePath = filePath;
        this.content = new String(Files.readAllBytes(Paths.get(filePath)));
        this.words = Arrays.asList(content.split("\\W+"));
        this.sentences = Arrays.asList(content.split("[.!?]\\s*"));
    }

    public int getTotalWords() {
        return words.size();
    }

    public int getTotalSentences() {
        return sentences.size();
    }

    public String getMostFrequentWord() {
        Map<String, Long> wordCounts = words.stream()
            .collect(Collectors.groupingBy(w -> w.toLowerCase(), Collectors.counting()));

        return wordCounts.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }

    public double getAverageWordLength() {
        return words.stream()
            .mapToInt(String::length)
            .average()
            .orElse(0.0);
    }

    public void printStatistics() {
        System.out.println("Número total de palabras: " + getTotalWords());
        System.out.println("Número total de oraciones: " + getTotalSentences());
        System.out.println("Palabra más frecuente: " + getMostFrequentWord());
        System.out.println("Longitud promedio de las palabras: " + getAverageWordLength());
    }
}
