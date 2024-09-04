package service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CharacterCounter {
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
}
