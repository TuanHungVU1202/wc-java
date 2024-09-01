package service;

import java.io.File;

public class SizeCounter {
    public static String MB = " MB";
    public static String KB = " KB";
    public static String BYTES = " Bytes";

    public static long getSizeInByte(File input) {
        if (null == input) {
            return -1;
        }

        return input.length();
    }

    public static long getSizeInKiloBytes(File input) {
        if (null == input) {
            return -1;
        }

        double sizeInKb = input.length() / 1024;
        return (long) sizeInKb;
    }

    public static long getSizeInMegabytes(File input) {
        if (null == input) {
            return -1;
        }

        double sizeInMb = input.length() / (1024 * 1024);
        return (long) sizeInMb;
    }
}
