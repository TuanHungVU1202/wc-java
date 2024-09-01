package service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SizeCounterTests {
    @Test
    public void shouldReturnMinusOneWhenInputIsNullForGetSizeInByteMethod() {
        System.out.println("shouldReturnMinusOneWhenInputIsNullForGetSizeInByteMethod");
        long expectedSize = -1;
        long actualSize = SizeCounter.getSizeInByte(null);
        assertEquals(expectedSize, actualSize);
    }
}