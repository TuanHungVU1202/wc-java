package service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CharacterCounter {
    /**
     * Counts the number of characters in a given file.
     *
     * @param input The File object representing the input file to be read.
     * @return The number of characters in the file, or -1 if the file is null, 
     *         cannot be read, is a directory, or if an IOException occurs.
     */
    public static int countCharacters(File input) {
        if (null == input || !input.canRead() || input.isDirectory()) {
            return -1;
        }

        int characterCount = 0;
        try (FileReader fileReader = new FileReader(input)) {
            while (fileReader.read() != -1) {
                characterCount++;
            }
        } catch (IOException e) {
            System.err.println("Error counting characters: " + e.getMessage());
            return -1;
        }

        return characterCount;
    }

    /**
     * Counts the number of characters in a given string.
     *
     * @param input The input string to count characters from.
     * @return The number of characters in the string, or -1 if the input is null.
     */
    public static int countCharacters(String input) {
        if (null == input) {
            return -1;
        }

        return input.length();
    }
}
