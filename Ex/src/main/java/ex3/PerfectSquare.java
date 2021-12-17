package ex3;

public final class PerfectSquare {
  public static boolean isPerfectSquare(int n) {
    if(n < 0) {
      return false;
    }
    int sqrt = (int) Math.round(Math.sqrt(n));
    return sqrt * sqrt == n;
  }
}