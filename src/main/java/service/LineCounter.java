package service;

import java.io.File;
import java.util.Scanner;

public class LineCounter {
    public static int countLines(File input) {
        if (null == input) {
            return -1;
        }

        int lineCount = 0;
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                lineCount++;
            }
        } catch (Exception e) {
            System.err.println("Error counting lines: " + e.getMessage());
        }

        return lineCount;
    }
}
