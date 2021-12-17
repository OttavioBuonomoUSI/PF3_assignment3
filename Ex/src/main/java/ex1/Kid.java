package ex1;

import java.util.concurrent.CyclicBarrier;

public final class Kid implements Runnable {

  private final Activity[] activities;
  private final CyclicBarrier barrier;

  public Kid(Activity[] activities, CyclicBarrier barrier) {
    this.activities = activities;
    this.barrier = barrier;
  }

  @Override
  public void run() {
    try {
      for (Activity activity : activities) {
        activity.perform();
        waitForOtherKids();
      }
    } catch (Exception e) {
      System.out.println("Exception occurred: " + e.getMessage());
      System.exit(1);
    }
  }

  private void waitForOtherKids() throws Exception {
    // TODO use the barrier to wait for all the other kids
    this.barrier.await();
  }
}