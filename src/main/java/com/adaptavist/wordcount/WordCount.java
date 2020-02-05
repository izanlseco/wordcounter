package com.adaptavist.wordcount;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCount {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("You should specify a file path as the first argument, i.e., \n" +
                               " -> java com.adaptavist.WordCount C:\\file\\path\\text-file.txt");
        } else {
            try {
                Stream<String> unorderedStream = getAndSplitByWords(args);
                Map<String, Integer> concurrentMap = groupByConcurrences(unorderedStream);
                sortAndPrintMap(concurrentMap);
            } catch (IOException exception) {
                System.out.print("The following error was given: " + exception + "\n"
                        + "Make sure that you are using the correct path.");
            }
        }
    }

    public static Stream<String> getAndSplitByWords(String[] args) throws IOException {
        return Files.lines(Paths
                .get(args[0])) //get the text-file from the first argument
                .flatMap(word -> Arrays.stream(word.toLowerCase().split("[(\\d+)(\\s+)(\\W+)]"))) //split by everything that is not a word
                .filter(isWord -> isWord.matches("(\\w+)")); //filter the words, this is needed because in some cases 'split()' leaves a space behind
    }

    public static Map<String, Integer> groupByConcurrences(Stream<String> unorderedStream) {
        return unorderedStream
                .parallel() // returns an equivalent stream that is parallel
                .collect(Collectors.toConcurrentMap(word -> word, value -> 1, Integer::sum)); //group the map by the number of concurrences
    }

    public static void sortAndPrintMap(Map<String, Integer> concurrentMap){
        System.out.println("These are the words ordered by its occurrences number:");
        concurrentMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())) //order the map from more to less
                .forEach(System.out::println); // use 'System.out.println()' with each pair
    }
}