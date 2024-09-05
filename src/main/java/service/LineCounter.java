package service;

import java.io.File;
import java.util.Scanner;

public class LineCounter {
    public static int countLines(File input) {
        if (null == input || !input.canRead() || input.isDirectory()) {
            return -1;
        }

        int lineCount = 0;
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                lineCount++;
                scanner.nextLine();
            }
        } catch (Exception e) {
            System.err.println("Error counting lines: " + e.getMessage());
            return -1;
        }

        return lineCount;
    }

    public static int countLines(String input) {
        if (null == input) {
            return -1;
        }
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '\n') {
                count++;
            }
        }
        return count + 1; // Add 1 to account for the last line without a newline character
    }
}
