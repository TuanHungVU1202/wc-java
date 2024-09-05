package service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CharacterCounter {
    public static int countCharacters(File input) {
        if (null == input || !input.canRead() || input.isDirectory()) {
            return -1;
        }

        int characterCount = 0;
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(input))) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                characterCount += bytesRead;
            }
        } catch (IOException e) {
            System.err.println("Error counting characters: " + e.getMessage());
            return -1;
        }

        return characterCount;
    }
}
