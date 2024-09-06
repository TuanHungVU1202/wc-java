package service;

import java.io.File;
import java.util.Scanner;

public class LineCounter {
    /**
     * Counts the number of lines in a given file.
     *
     * @param input The File object representing the input file to be read.
     * @return The number of lines in the file, or -1 if the file is null, 
     *         cannot be read, is a directory, or if an exception occurs.
     */
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

    /**
     * Counts the number of lines in a given string.
     *
     * @param input The input string to count lines from.
     * @return The number of lines in the string, or -1 if the input is null.
     *         Note: This method counts newline characters and adds 1 to account
     *         for the last line without a newline character.
     */
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
