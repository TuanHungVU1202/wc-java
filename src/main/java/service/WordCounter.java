package service;

import java.io.File;
import java.util.Scanner;

public class WordCounter {
    /**
     * Counts the number of words in a given file.
     *
     * @param input The File object representing the input file to be read.
     * @return The number of words in the file, or -1 if the file is null, 
     *         cannot be read, or is a directory. Returns 0 if the file is empty.
     */
    public static int countWords(File input) {
        if (null == input || !input.canRead() || input.isDirectory()) {
            return -1;
        }

        int wordCount = 0;
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNext()) {
                wordCount++;
                scanner.next();
            }
        } catch (Exception e) {
            System.err.println("Error counting words: " + e.getMessage());
        }

        return wordCount;
    }

    /**
     * Counts the number of words in a given string.
     *
     * @param input The input string to count words from.
     * @return The number of words in the string, or -1 if the input is null.
     *         Returns 0 if the input string is empty or contains only whitespace.
     */
    public static int countWords(String input) {
        if (null == input) {
            return -1;
        }

        if (input.isEmpty() || input.isBlank()) {
            return 0;
        }

        return input.trim().split("\\s+").length;
    }
}
