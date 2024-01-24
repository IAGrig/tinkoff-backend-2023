package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class ParametersGenerator {

    private ParametersGenerator() {
    }

    public static int generateInt(Annotation[] annotations) {
        Annotation minAnnotation = null;
        Annotation maxAnnotation = null;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                minAnnotation = annotation;
            }
            if (annotation instanceof Max) {
                maxAnnotation = annotation;
            }
        }
        final int MIN = minAnnotation == null ? Integer.MIN_VALUE : (int) ((Min) minAnnotation).value();
        final int MAX = maxAnnotation == null ? Integer.MAX_VALUE : (int) ((Max) maxAnnotation).value();
        return ThreadLocalRandom.current().nextInt(MIN, MAX);
    }

    public static long generateLong(Annotation[] annotations) {
        Annotation minAnnotation = null;
        Annotation maxAnnotation = null;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                minAnnotation = annotation;
            }
            if (annotation instanceof Max) {
                maxAnnotation = annotation;
            }
        }
        final long MIN = minAnnotation == null ? Long.MIN_VALUE : ((Min) minAnnotation).value();
        final long MAX = maxAnnotation == null ? Long.MAX_VALUE : ((Max) maxAnnotation).value();
        return ThreadLocalRandom.current().nextLong(MIN, MAX);
    }

    public static double generateDouble(Annotation[] annotations) {
        Annotation minAnnotation = null;
        Annotation maxAnnotation = null;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                minAnnotation = annotation;
            }
            if (annotation instanceof Max) {
                maxAnnotation = annotation;
            }
        }
        final double MIN = minAnnotation == null ? Double.MIN_VALUE : ((Min) minAnnotation).value();
        final double MAX = maxAnnotation == null ? Double.MAX_VALUE : ((Max) maxAnnotation).value();
        return ThreadLocalRandom.current().nextDouble(MIN, MAX);
    }

    public static float generateFloat(Annotation[] annotations) {
        Annotation minAnnotation = null;
        Annotation maxAnnotation = null;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                minAnnotation = annotation;
            }
            if (annotation instanceof Max) {
                maxAnnotation = annotation;
            }
        }
        final float MIN = minAnnotation == null ? Float.MIN_VALUE : ((Min) minAnnotation).value();
        final float MAX = maxAnnotation == null ? Float.MAX_VALUE : ((Max) maxAnnotation).value();
        return ThreadLocalRandom.current().nextFloat(MIN, MAX);
    }

    public static String generateString(Annotation[] annotations) {
        final int MIN_LENGTH_DEFAULT = 3;
        final int MAX_LENGTH_DEFAULT = 10;
        final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        int minLength = MIN_LENGTH_DEFAULT;
        int maxLength = MAX_LENGTH_DEFAULT;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                minLength = Integer.min(minLength, (int) ((Min) annotation).value());
            }
            if (annotation instanceof Max) {
                maxLength = Integer.max(maxLength, (int) ((Max) annotation).value());
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(minLength, maxLength); i++) {
            int letterIndex = ThreadLocalRandom.current().nextInt(0, alphabet.length);
            stringBuilder.append(alphabet[letterIndex]);
        }
        return stringBuilder.toString();
    }

    public static boolean generateBoolean(Annotation[] annotations) {
        return ThreadLocalRandom.current().nextBoolean();
    }
}
