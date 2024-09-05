package service;

import java.io.File;
import java.util.Scanner;

public class WordCounter {
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
}
