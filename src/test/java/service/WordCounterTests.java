package service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;

public class WordCounterTests {
    @Test
    public void shouldCountWordsInFile() {
        File file = new File("src/test/resources/test.txt");
        int wordCount = WordCounter.countWords(file);
        assertEquals(58164, wordCount);
    }

    @Test
    public void shouldReturnNegativeOneForNullFile() {
        int wordCount = WordCounter.countWords((File) null);
        assertEquals(-1, wordCount);
    }

    @Test
    public void shouldReturnNegativeOneForNonExistentFile() throws Exception {
        File nonExistentFile = File.createTempFile("non_existent", ".txt");
        nonExistentFile.delete(); // Ensure the file doesn't exist
        int wordCount = WordCounter.countWords(nonExistentFile);
        assertEquals(-1, wordCount);
    }

    @Test
    public void shouldReturnNegativeOneForDirectory() {
        File directory = new File("src/test/resources");
        int wordCount = WordCounter.countWords(directory);
        assertEquals(-1, wordCount);
    }

    @Test
    public void shouldCountWordsInEmptyFile() throws Exception {
        File emptyFile = File.createTempFile("empty", ".txt");
        emptyFile.deleteOnExit();
        int wordCount = WordCounter.countWords(emptyFile);
        assertEquals(0, wordCount);
    }

    @Test
    public void shouldCountWordsInSingleWordFile() throws Exception {
        File singleWordFile = File.createTempFile("single_word", ".txt");
        singleWordFile.deleteOnExit();
        java.nio.file.Files.write(singleWordFile.toPath(), "word".getBytes());
        int wordCount = WordCounter.countWords(singleWordFile);
        assertEquals(1, wordCount);
    }

    @Test
    public void shouldCountWordsInMultiLineFile() throws Exception {
        File multiLineFile = File.createTempFile("multi_line", ".txt");
        multiLineFile.deleteOnExit();
        Files.write(multiLineFile.toPath(),
                "This is a test.\nIt has multiple lines.\nTotal eleven words.".getBytes());
        int wordCount = WordCounter.countWords(multiLineFile);
        assertEquals(11, wordCount);
    }

    @Test
    public void shouldReturnNegativeOneForNullString() {
        int wordCount = WordCounter.countWords((String) null);
        assertEquals(-1, wordCount);
    }

    @Test
    public void shouldCountWordsInEmptyString() {
        int wordCount = WordCounter.countWords("");
        assertEquals(0, wordCount);
    }

    @Test
    public void shouldCountWordsInSingleWordString() {
        int wordCount = WordCounter.countWords("word");
        assertEquals(1, wordCount);
    }

    @Test
    public void shouldCountWordsInMultiLineString() {
        String multiLineString = "This is a test.\nIt has multiple lines.\nTotal eleven words.";
        int wordCount = WordCounter.countWords(multiLineString);
        assertEquals(11, wordCount);
    }

    @Test
    public void shouldHandleStringWithMultipleSpaces() {
        String stringWithMultipleSpaces = "This   string   has   multiple   spaces";
        int wordCount = WordCounter.countWords(stringWithMultipleSpaces);
        assertEquals(5, wordCount);
    }

    @Test
    public void shouldHandleStringWithLeadingAndTrailingSpaces() {
        String stringWithExtraSpaces = "  Leading and trailing spaces  ";
        int wordCount = WordCounter.countWords(stringWithExtraSpaces);
        assertEquals(4, wordCount);
    }

    @Test
    public void shouldHandleLargeString() {
        StringBuilder largeStringBuilder = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeStringBuilder.append("word ");
        }
        String largeString = largeStringBuilder.toString().trim();

        int wordCount = WordCounter.countWords(largeString);
        assertEquals(100000, wordCount);
    }
}
