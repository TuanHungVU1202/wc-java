package service;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
