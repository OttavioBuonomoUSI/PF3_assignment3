package ex2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

public final class Ex2Test {

    @Test
    public void testEx2() throws InterruptedException {
        Ex2Simulator simulator = new Ex2Simulator();
        VoteCounter counter = simulator.run();
        int count = Arrays.stream(Ex2Simulator.ACTIVITIES).mapToInt(counter::getVoteCount).sum();
        Assertions.assertEquals(Ex2Simulator.N_KIDS, count);
    }
}
