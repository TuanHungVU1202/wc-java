import java.io.File;
import java.util.Scanner;

import service.CharacterCounter;
import service.LineCounter;
import service.ManPage;
import service.SizeCounter;
import service.WordCounter;

public class WordCount {
    public static void main(String[] args) {
        String option = "";
        String input = null;

        switch (args.length) {
            case 0:
                // Read from standard input with default option
                handleStandardInput(option);
                return;
            case 1:
                if (args[0].equals("-h")) {
                    ManPage.showHelpPage();
                    return;
                } else if (args[0].startsWith("-")) {
                    option = args[0];
                } else {
                    input = args[0];
                }
                break;
            case 2:
                option = args[0];
                input = args[1];
                break;
            default:
                System.out.println("Usage: java WordCount [-c|-l|-w|-m] [<filename or \"string\">]");
                System.out.println("For more information, use the -h option: java WordCount -h");
                return;
        }

        if (input == null) {
            // Read from standard input
            handleStandardInput(option);
        } else if (input.startsWith("\"") && input.endsWith("\"")) {
            // Handle as a string input
            handleStringInput(option, input.substring(1, input.length() - 1));
        } else {
            // Handle as a file input
            handleFileInput(option, input);
        }
    }

    private static void handleFileInput(String option, String fileName) {
        File inputFile = new File(fileName);
        if (!inputFile.exists() || !inputFile.isFile()) {
            System.out.println("Error: File not found or is not a regular file.");
            return;
        }

        int lineCount = 0, wordCount = 0, byteCount = 0, charCount = 0;

        switch (option) {
            case "-c":
                byteCount = (int) SizeCounter.getSizeInByte(inputFile);
                if (byteCount != -1) {
                    System.out.printf("%7d %s%n", byteCount, fileName);
                }
                break;
            case "-l":
                lineCount = LineCounter.countLines(inputFile);
                if (lineCount != -1) {
                    System.out.printf("%7d %s%n", lineCount, fileName);
                }
                break;
            case "-w":
                wordCount = WordCounter.countWords(inputFile);
                if (wordCount != -1) {
                    System.out.printf("%7d %s%n", wordCount, fileName);
                }
                break;
            case "-m":
                charCount = CharacterCounter.countCharacters(inputFile);
                if (charCount != -1) {
                    System.out.printf("%7d %s%n", charCount, fileName);
                }
                break;
            case "":
                lineCount = LineCounter.countLines(inputFile);
                wordCount = WordCounter.countWords(inputFile);
                byteCount = (int) SizeCounter.getSizeInByte(inputFile);
                if (lineCount != -1 && wordCount != -1 && byteCount != -1) {
                    System.out.printf("%7d %7d %7d %s%n", lineCount, wordCount, byteCount, fileName);
                }
                break;
            default:
                System.out.println("Invalid option. Use -c, -l, -w, -m, or no option.");
                return;
        }

        if (lineCount == -1 || wordCount == -1 || byteCount == -1 || charCount == -1) {
            System.out.println("Error: Unable to process the file.");
        }
    }

    private static void handleStringInput(String option, String input) {
        processInput(option, input);
    }

    private static void handleStandardInput(String option) {
        StringBuilder input = new StringBuilder();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                input.append(scanner.nextLine()).append("\n");
            }
        }

        String inputString = input.toString();
        processInput(option, inputString);
    }

    private static void processInput(String option, String input) {
        int lineCount = 0, wordCount = 0, byteCount = 0, charCount = 0;

        switch (option) {
            case "-c":
                byteCount = (int) SizeCounter.getSizeInByte(input);
                if (byteCount != -1) {
                    System.out.printf("%7d%n", byteCount);
                }
                break;
            case "-l":
                lineCount = LineCounter.countLines(input);
                if (lineCount != -1) {
                    System.out.printf("%7d%n", lineCount);
                }
                break;
            case "-w":
                wordCount = WordCounter.countWords(input);
                if (wordCount != -1) {
                    System.out.printf("%7d%n", wordCount);
                }
                break;
            case "-m":
                charCount = input.length();
                System.out.printf("%7d%n", charCount);
                break;
            case "":
                lineCount = LineCounter.countLines(input);
                wordCount = WordCounter.countWords(input);
                byteCount = (int) SizeCounter.getSizeInByte(input);
                if (lineCount != -1 && wordCount != -1 && byteCount != -1) {
                    System.out.printf("%7d %7d %7d%n", lineCount, wordCount, byteCount);
                }
                break;
            default:
                System.out.println("Invalid option. Use -c, -l, -w, -m, or no option.");
                return;
        }

        if (lineCount == -1 || wordCount == -1 || byteCount == -1) {
            System.out.println("Error: Unable to process the input.");
        }
    }
}