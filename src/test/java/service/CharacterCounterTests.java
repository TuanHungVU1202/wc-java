package service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;

public class CharacterCounterTests {
    @Test
    public void shouldReturnMinusOneWhenInputIsNull() {
        int characterCount = CharacterCounter.countCharacters((File) null);
        assertEquals(-1, characterCount);
    }

    @Test
    public void shouldCountCharactersInFile() throws Exception {
        File file = new File("src/test/resources/test.txt");
        Process process = Runtime.getRuntime().exec(new String[]{"wc", "-m", file.getAbsolutePath()});
        process.waitFor();
        String output = new String(process.getInputStream().readAllBytes()).trim();
        int expectedCharacterCount = Integer.parseInt(output.split("\\s+")[0]);
        
        int characterCount = CharacterCounter.countCharacters(file);
        assertEquals(expectedCharacterCount, characterCount);
    }

    @Test
    public void shouldReturnNegativeOneForNonExistentFile() throws Exception {
        File nonExistentFile = File.createTempFile("non_existent", ".txt");
        nonExistentFile.delete(); // Ensure the file doesn't exist
        int characterCount = CharacterCounter.countCharacters(nonExistentFile);
        assertEquals(-1, characterCount);
    }

    @Test
    public void shouldReturnNegativeOneForDirectory() {
        File directory = new File("src/test/resources");
        int characterCount = CharacterCounter.countCharacters(directory);
        assertEquals(-1, characterCount);
    }

    @Test
    public void shouldCountCharactersInEmptyFile() throws Exception {
        File emptyFile = File.createTempFile("empty", ".txt");
        emptyFile.deleteOnExit();
        int characterCount = CharacterCounter.countCharacters(emptyFile);
        assertEquals(0, characterCount);
    }

    @Test
    public void shouldCountCharactersInSingleCharacterFile() throws Exception {
        File singleCharFile = File.createTempFile("single_char", ".txt");
        singleCharFile.deleteOnExit();
        Files.write(singleCharFile.toPath(), "a".getBytes());
        int characterCount = CharacterCounter.countCharacters(singleCharFile);
        assertEquals(1, characterCount);
    }

    @Test
    public void shouldCountCharactersInMultiLineFileIncludingNewLines() throws Exception {
        File multiLineFile = File.createTempFile("multi_line", ".txt");
        multiLineFile.deleteOnExit();
        Files.write(multiLineFile.toPath(),
                "This is a test.\nIt has multiple lines.\nTotal 59 characters.".getBytes());
        int characterCount = CharacterCounter.countCharacters(multiLineFile);
        assertEquals(59, characterCount);
    }

    @Test
    public void shouldHandleLargeFilesWithoutRunningOutOfMemory() throws Exception {
        int expectedCharacterCount = 1000000;
        File largeFile = File.createTempFile("large", ".txt");
        largeFile.deleteOnExit();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < expectedCharacterCount; i++) {
            builder.append("a");
        }
        Files.write(largeFile.toPath(), builder.toString().getBytes());

        int actualCharacterCount = CharacterCounter.countCharacters(largeFile);
        assertEquals(expectedCharacterCount, actualCharacterCount);
    }

    @Test
    public void shouldReturnNegativeOneForNullString() {
        int characterCount = CharacterCounter.countCharacters((String) null);
        assertEquals(-1, characterCount);
    }

    @Test
    public void shouldCountCharactersInEmptyString() {
        int characterCount = CharacterCounter.countCharacters("");
        assertEquals(0, characterCount);
    }

    @Test
    public void shouldCountCharactersInSingleCharacterString() {
        int characterCount = CharacterCounter.countCharacters("a");
        assertEquals(1, characterCount);
    }

    @Test
    public void shouldCountCharactersInMultiLineStringIncludingNewLines() {
        String multiLineString = "This is a test.\nIt has multiple lines.\nTotal 59 characters.";
        int characterCount = CharacterCounter.countCharacters(multiLineString);
        assertEquals(59, characterCount);
    }

    @Test
    public void shouldHandleLargeStringsWithoutRunningOutOfMemory() {
        int expectedCharacterCount = 1000000;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < expectedCharacterCount; i++) {
            builder.append("a");
        }
        String largeString = builder.toString();

        int actualCharacterCount = CharacterCounter.countCharacters(largeString);
        assertEquals(expectedCharacterCount, actualCharacterCount);
    }
}
