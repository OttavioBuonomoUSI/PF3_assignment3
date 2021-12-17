package ex1;

import java.util.concurrent.CyclicBarrier;


public final class Ex1Simulator {

  public static void main(String[] args) {
    new Ex1Simulator().run();
  }

  private static final int N_KIDS = 5;
  private static final int N_ACTIVITIES = 10;

  public void run() {
    Activity[] activities = new Activity[N_ACTIVITIES];
    for (int i = 0; i < activities.length; i++) {
      activities[i] = new Activity("Activity " + i);
    }
    // TODO instantiate a CyclicBarrier
    CyclicBarrier barrier = new CyclicBarrier(N_KIDS, new ActivityCleaner());

    for (int i = 0; i < N_KIDS; i++) {
      new Thread(new Kid(activities, barrier), "Kid #" + i).start();
    }
  }
}