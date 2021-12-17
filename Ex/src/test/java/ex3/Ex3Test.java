package ex3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.stream.IntStream;

public final class Ex3Test {

    private static final int LOW = 1;
    private static final int HIGH = 1_000_000;
    private static final int[] NUMBERS = IntStream.range(LOW, HIGH).toArray();
    private static final int EXPECTED = new SequentialPerfectSquareCounter().countPerfectSquares(NUMBERS);

    private void testPerfectSquareCounter(PerfectSquareCounter counter) throws Exception {
        Assertions.assertEquals(EXPECTED, counter.countPerfectSquares(NUMBERS));
    }

    @Test
    public void testThreadPool() throws Exception {
        testPerfectSquareCounter(new ThreadPoolPerfectSquareCounter());
    }

    @Test
    public void testForkJoinPool() throws Exception {
        testPerfectSquareCounter(new ForkJoinPerfectSquareCounter());
    }

}
