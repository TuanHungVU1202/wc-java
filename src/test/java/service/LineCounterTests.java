package service;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LineCounterTests {
    @Test
    public void shouldReturnMinusOneWhenInputIsNull() {
        int lineCount = LineCounter.countLines(null);
        assertEquals(-1, lineCount);
    }

    @Test
    public void shouldReturnZeroWhenInputFileIsEmpty() throws Exception {
        File emptyFile = File.createTempFile("empty", ".txt");
        emptyFile.deleteOnExit();

        int lineCount = LineCounter.countLines(emptyFile);
        assertEquals(0, lineCount);
    }

    @Test
    public void shouldHandleLargeFilesWithoutRunningOutOfMemory() throws Exception {
        // Create a large temporary file with a known number of lines
        int expectedLineCount = 1000000;
        File largeFile = File.createTempFile("large", ".txt");
        largeFile.deleteOnExit();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < expectedLineCount; i++) {
            builder.append("Line ").append(i).append("\n");
        }
        Files.write(largeFile.toPath(), builder.toString().getBytes());

        int actualLineCount = LineCounter.countLines(largeFile);
        assertEquals(expectedLineCount, actualLineCount);
    }

    @Test
    public void shouldHandleDifferentLineEndings() throws Exception {
        // Create a temporary file with a mix of CRLF and LF line endings
        File fileWithMixedLineEndings = File.createTempFile("mixed", ".txt");
        fileWithMixedLineEndings.deleteOnExit();
        StringBuilder builder = new StringBuilder();
        builder.append("Line 1\r\n");
        builder.append("Line 2\n");
        builder.append("Line 3\r\n");
        Files.write(fileWithMixedLineEndings.toPath(), builder.toString().getBytes());

        int actualLineCount = LineCounter.countLines(fileWithMixedLineEndings);
        assertEquals(3, actualLineCount);
    }

    @Test
    public void shouldHandleFilesWithUnicodeCharacters() throws Exception {
        // Create a temporary file with Unicode characters
        File fileWithUnicodeChars = File.createTempFile("unicode", ".txt");
        fileWithUnicodeChars.deleteOnExit();
        StringBuilder builder = new StringBuilder();
        builder.append("Line 1\n");
        builder.append("Line 2 with é\n");
        builder.append("Line 3 with 你好\n");
        Files.write(fileWithUnicodeChars.toPath(), builder.toString().getBytes(StandardCharsets.UTF_8));

        int actualLineCount = LineCounter.countLines(fileWithUnicodeChars);
        assertEquals(3, actualLineCount);
    }

    @Test
    public void shouldReturnMinusOneWhenFileCannotBeRead() throws Exception {
        // Create a temporary file with read permissions disabled
        File unreadableFile = File.createTempFile("unreadable", ".txt");
        unreadableFile.deleteOnExit();
        unreadableFile.setReadable(false);

        int lineCount = LineCounter.countLines(unreadableFile);
        assertEquals(-1, lineCount);
    }

    @Test
    public void shouldReturnMinusOneWhenFilePathIsInvalid() {
        File invalidFilePath = new File("/path/does/not/exist");

        int lineCount = LineCounter.countLines(invalidFilePath);
        assertEquals(-1, lineCount);
    }

    @Test
    public void shouldHandleFilesWithBinaryData() throws Exception {
        // Create a temporary file with binary data
        File fileWithBinaryData = File.createTempFile("binary", ".dat");
        fileWithBinaryData.deleteOnExit();
        byte[] binaryData = { 0x01, 0x02, 0x03, 0x04, 0x05 };
        Files.write(fileWithBinaryData.toPath(), binaryData);

        int actualLineCount = LineCounter.countLines(fileWithBinaryData);
        assertEquals(1, actualLineCount);
    }

    @Test
    public void shouldHandleFileFromResources() throws Exception {
        // Get the file from resources
        File testFile = new File("src/test/resources/test.txt");

        int actualLineCount = LineCounter.countLines(testFile);
        assertEquals(7145, actualLineCount);
    }

    @Test
    public void shouldHandleEmptyLinesInFile() throws Exception {
        // Get the file from resources
        File testFile = new File("src/test/resources/test.txt");

        int actualLineCount = LineCounter.countLines(testFile);
        assertEquals(7145, actualLineCount, "File should have 7145 lines");

        // Read the file content to verify empty lines
        List<String> lines = Files.readAllLines(testFile.toPath());
        long emptyLines = lines.stream().filter(String::isEmpty).count();
        assertTrue(emptyLines > 0, "File should contain empty lines");
        assertEquals(lines.size(), actualLineCount, "Line count should match total lines including empty ones");
    }

    @Test
    public void shouldHandleFileWithOnlyEmptyLines() throws Exception {
        // Create a temporary file with only empty lines
        File emptyLinesFile = File.createTempFile("emptyLines", ".txt");
        emptyLinesFile.deleteOnExit();
        List<String> emptyLines = Arrays.asList("", "", "");
        Files.write(emptyLinesFile.toPath(), emptyLines);

        int actualLineCount = LineCounter.countLines(emptyLinesFile);
        assertEquals(3, actualLineCount);
    }
}
