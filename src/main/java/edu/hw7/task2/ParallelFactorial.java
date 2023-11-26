package edu.hw7.task2;

import com.sun.jdi.event.StepEvent;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelFactorial {
    public static long calculateFactorial(long n){
        return LongStream.range(1, n).parallel().reduce((a, b) -> a*b).getAsLong();
    }
}
