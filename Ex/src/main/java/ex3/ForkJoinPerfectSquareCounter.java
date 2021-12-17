package ex3;

import java.util.List;
import java.util.concurrent.*;

public final class ForkJoinPerfectSquareCounter implements PerfectSquareCounter {
  private static final int SEQUENTIAL_THRESHOLD = 1000;

  @Override
  public int countPerfectSquares(int[] nums) {
    CountPerfectSquarePortion task =
            new CountPerfectSquarePortion(nums, 0, nums.length);

    // TODO 1) execute the CountPerfectSquare task on
    //  the common ForkJoinPool and return the result
    ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
    return forkJoinPool.invoke(task);
  }

  private static class CountPerfectSquarePortion extends RecursiveTask<Integer> {
    private final int low, high;
    private final int[] nums;

    public CountPerfectSquarePortion(int[] nums, int low, int high) {
      this.nums = nums; this.low = low; this.high = high;
    }

    @Override
    protected Integer compute() {
      int size = high - low;
      if (size <= SEQUENTIAL_THRESHOLD) {
        return SequentialPerfectSquareCounter
                .countPerfectSquares(nums, low, high);
      }
      else {
        int mid = low + size / 2;
        // TODO 2) Complete method compute by creating two
        //  CountPerfectSquare recursive tasks for computing
        //  the count of the first and second half of the input array;
        //  execute them in parallel and return the sum of their result
        CountPerfectSquarePortion firstHalf = new CountPerfectSquarePortion(this.nums, this.low, mid);
        CountPerfectSquarePortion secondHalf = new CountPerfectSquarePortion(this.nums, mid, this.high);
        firstHalf.fork();
        secondHalf.fork();
        return firstHalf.join() + secondHalf.join();
      }
    }
  }
}
