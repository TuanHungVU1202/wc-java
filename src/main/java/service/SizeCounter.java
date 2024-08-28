package service;

import java.io.File;

public class SizeCounter {
    private static String MB = " MB";
    private static String KB = " KB";
    private static String BYTES = " Bytes";

    public static String getSizeInByte(File input) {
        if (null == input) {
            return "Invalid input";
        }

        return input.length() + BYTES;
    }

    public static String getSizeInKiloBytes(File input) {
        if (null == input) {
            return "Invalid input";
        }

        double sizeInKb = input.length() / 1024;
        return sizeInKb + KB;
    }

    public static String getSizeInMegabytes(File input) {
        if (null == input) {
            return "Invalid input";
        }

        double sizeInMb = input.length() / (1024 * 1024);
        return sizeInMb + MB;
    }
}
