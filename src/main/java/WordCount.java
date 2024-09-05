import service.CharacterCounter;
import service.LineCounter;
import service.SizeCounter;
import service.WordCounter;

public class WordCount {
    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2) {
            System.out.println("Usage: java WordCount [-c|-l|-w|-m] <filename>");
            return;
        }

        String option = "";
        String fileName;
        if (args.length == 2) {
            option = args[0];
            fileName = args[1];
        } else {
            fileName = args[0];
        }

        java.io.File inputFile = new java.io.File(fileName);
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
}