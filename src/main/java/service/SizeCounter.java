package service;

import java.io.File;

public class SizeCounter {
    public static String MB = " MB";
    public static String KB = " KB";
    public static String BYTES = " Bytes";

    /**
     * Gets the size of a file in bytes.
     *
     * @param input The File object to measure.
     * @return The size of the file in bytes, or -1 if the input is null.
     */
    public static long getSizeInByte(File input) {
        if (null == input) {
            return -1;
        }

        return input.length();
    }

    /**
     * Gets the size of a file in kilobytes.
     *
     * @param input The File object to measure.
     * @return The size of the file in kilobytes, or -1 if the input is null.
     */
    public static long getSizeInKiloBytes(File input) {
        if (null == input) {
            return -1;
        }

        double sizeInKb = input.length() / 1024;
        return (long) sizeInKb;
    }

    /**
     * Gets the size of a file in megabytes.
     *
     * @param input The File object to measure.
     * @return The size of the file in megabytes, or -1 if the input is null.
     */
    public static long getSizeInMegabytes(File input) {
        if (null == input) {
            return -1;
        }

        double sizeInMb = input.length() / (1024 * 1024);
        return (long) sizeInMb;
    }

    /**
     * Gets the size of a string in bytes.
     *
     * @param input The input string to measure.
     * @return The size of the string in bytes, or -1 if the input is null.
     */
    public static long getSizeInByte(String input) {
        if (null == input) {
            return -1;
        }

        return input.length();
    }

    /**
     * Gets the size of a string in kilobytes.
     *
     * @param input The input string to measure.
     * @return The size of the string in kilobytes, or -1 if the input is null.
     */
    public static long getSizeInKiloBytes(String input) {
        if (null == input) {
            return -1;
        }

        double sizeInKb = input.length() / 1024;
        return (long) sizeInKb;
    }

    /**
     * Gets the size of a string in megabytes.
     *
     * @param input The input string to measure.
     * @return The size of the string in megabytes, or -1 if the input is null.
     */
    public static long getSizeInMegabytes(String input) {
        if (null == input) {
            return -1;
        }

        double sizeInMb = input.length() / (1024 * 1024);
        return (long) sizeInMb;
    }
}
