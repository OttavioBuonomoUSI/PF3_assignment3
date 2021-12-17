package ex1;


public final class ActivityCleaner implements Runnable {
  @Override
  public void run() {
    System.out.println("Cleaning done. The current activity has been completed.");
  }
}