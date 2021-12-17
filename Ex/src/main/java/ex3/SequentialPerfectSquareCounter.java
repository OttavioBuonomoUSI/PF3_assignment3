package ex3;

public final class SequentialPerfectSquareCounter implements PerfectSquareCounter {

  public static int countPerfectSquares(int[] nums, int from, int to) {
    int count = 0;
    for(int i = from; i < to; i++) {
      if(PerfectSquare.isPerfectSquare(nums[i])) {
        count++;
      }
    }
    return count;
  }

  @Override
  public int countPerfectSquares(int[] nums) {
    return countPerfectSquares(nums, 0, nums.length);
  }
}
