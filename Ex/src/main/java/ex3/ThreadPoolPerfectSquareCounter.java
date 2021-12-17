package ex3;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public final class ThreadPoolPerfectSquareCounter implements PerfectSquareCounter {
  private static final int SEQUENTIAL_THRESHOLD = 1000;

  @Override
  public int countPerfectSquares(int[] nums) throws Exception {
    List<Callable<Integer>> tasks = new LinkedList<>();
    int from = 0, to = SEQUENTIAL_THRESHOLD;
    while(to < nums.length) {
      tasks.add(new CountPerfectSquarePortion(nums, from, to));
      from = to;
      to += SEQUENTIAL_THRESHOLD;
    }
    tasks.add(new CountPerfectSquarePortion(nums, from, nums.length));

    // TODO 1) instantiate an ExecutorService with a fixed number of threads
    //  equals to the number of available processors in the system
    ExecutorService executor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            16,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>()
    );

    // TODO 2) execute all tasks with the executor and get a List<Future<Integer>>
    List<Future<Integer>> futures = executor.invokeAll(tasks);

    // TODO 3) shutdown the threadpool and wait for its termination
    executor.shutdownNow();

    // TODO 4) add to count all integer values in the List<Future<Integer>>
    int count = 0;
    for (Future<Integer> future : futures) {
      int i = future.get();
      count += i;
    }
    return count;
  }

  private static class CountPerfectSquarePortion implements Callable<Integer> {
    private final int[] nums;
    private final int low, high;

    private CountPerfectSquarePortion(int[] nums, int low, int high) {
      this.nums = nums; this.low = low; this.high = high;
    }

    @Override
    public Integer call() {
      return SequentialPerfectSquareCounter
              .countPerfectSquares(nums, low, high);
    }
  }
}
