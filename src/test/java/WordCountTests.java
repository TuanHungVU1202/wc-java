import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;

public class WordCountTests {
    private Path m_TestFilePath = Path.of("src/test/resources/test.txt");

    @Test
    void testDefaultOption() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        WordCount.main(new String[]{m_TestFilePath.toString()});

        ProcessBuilder pb = new ProcessBuilder("wc", m_TestFilePath.toString());
        Process process = pb.start();
        String expected = new String(process.getInputStream().readAllBytes()).trim().replaceAll("\\s+", " ");
        String actual = outContent.toString().trim().replaceAll("\\s+", " ");
        assertEquals(expected, actual);
    }

    @Test
    void testLineCount() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        WordCount.main(new String[]{"-l", m_TestFilePath.toString()});

        ProcessBuilder pb = new ProcessBuilder("wc", "-l", m_TestFilePath.toString());
        Process process = pb.start();
        String expected = new String(process.getInputStream().readAllBytes()).trim();
        String actual = outContent.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    void testWordCount() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        WordCount.main(new String[]{"-w", m_TestFilePath.toString()});

        ProcessBuilder pb = new ProcessBuilder("wc", "-w", m_TestFilePath.toString());
        Process process = pb.start();
        String expected = new String(process.getInputStream().readAllBytes()).trim();
        String actual = outContent.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    void testByteCount() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        WordCount.main(new String[]{"-c", m_TestFilePath.toString()});

        ProcessBuilder pb = new ProcessBuilder("wc", "-c", m_TestFilePath.toString());
        Process process = pb.start();
        String expected = new String(process.getInputStream().readAllBytes()).trim();
        String actual = outContent.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    void testCharacterCount() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        WordCount.main(new String[]{"-m", m_TestFilePath.toString()});

        ProcessBuilder pb = new ProcessBuilder("wc", "-m", m_TestFilePath.toString());
        Process process = pb.start();
        String expected = new String(process.getInputStream().readAllBytes()).trim();
        String actual = outContent.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    void testInvalidOption() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        WordCount.main(new String[]{"-x", m_TestFilePath.toString()});

        String expected = "Invalid option. Use -c, -l, -w, -m, or no option.";
        String actual = outContent.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    void testFileNotFound() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        WordCount.main(new String[]{"nonexistent.txt"});

        String expected = "Error: File not found or is not a regular file.";
        String actual = outContent.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    void testStringInput() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        WordCount.main(new String[]{"-w", "\"Hello world\""});

        String expected = "      2".trim();
        String actual = outContent.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    void testStringInputWithMultipleOptions() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        WordCount.main(new String[]{"\"Hello world\""});

        String expected = "      1       2      11".trim();
        String actual = outContent.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    void testStandardInput() throws IOException {
        String input = "Hello\nWorld\n";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        WordCount.main(new String[]{});

        String expected = "      3       2      12".trim();
        String actual = outContent.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    void testStandardInputWithOption() throws IOException {
        String input = "Hello\nWorld\n";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        WordCount.main(new String[]{"-l"});

        String expected = "      3".trim();
        String actual = outContent.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    void testPipedInput() throws IOException {
        String input = "This is a test\nof piped input\n";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        WordCount.main(new String[]{"-w"});

        String expected = "      7".trim();
        String actual = outContent.toString().trim();
        assertEquals(expected, actual);
    }
}
