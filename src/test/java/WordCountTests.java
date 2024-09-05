import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
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
}
