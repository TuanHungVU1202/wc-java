package service;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SizeCounterTests {
    String m_TestFileStr = "src/test/resources/test.txt";

    @Test
    public void shouldReturnMinusOneWhenInputIsNullForGetSizeInByteMethod() {
        long expectedSize = -1;
        long actualSize = SizeCounter.getSizeInByte(null);
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void shouldReturnMinusOneWhenInputIsNullForGetSizeInKiloBytesMethod() {
        long expectedSize = -1;
        long actualSize = SizeCounter.getSizeInKiloBytes(null);
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void shouldReturnMinusOneWhenInputIsNullForGetSizeInMegabytesMethod() {
        long expectedSize = -1;
        long actualSize = SizeCounter.getSizeInMegabytes(null);
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void shouldReturnCorrectSizeInBytesWhenInputFileExists() {
        long expectedSize = new File(m_TestFileStr).length();
        long actualSize = SizeCounter.getSizeInByte(new File(m_TestFileStr));
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void shouldReturnCorrectSizeInKiloBytesWhenInputFileExists() {
        long expectedSize = new File(m_TestFileStr).length() / 1024;
        long actualSize = SizeCounter.getSizeInKiloBytes(new File(m_TestFileStr));
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void shouldReturnCorrectSizeInMegabytesWhenInputFileExists() {
        long expectedSize = new File(m_TestFileStr).length() / (1024 * 1024);
        long actualSize = SizeCounter.getSizeInMegabytes(new File(m_TestFileStr));
        assertEquals(expectedSize, actualSize);
    }
}