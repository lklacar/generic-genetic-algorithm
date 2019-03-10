package com.lukaklacar.geneticalgorithm.util;

import java.util.concurrent.ThreadLocalRandom;

public class MathUtil {
    public static int randomInteger(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static byte[] randomByteArray(int length) {
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = (byte) randomInteger(0, 255);
        }

        return bytes;
    }
}
