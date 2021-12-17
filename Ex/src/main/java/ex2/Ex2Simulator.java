package ex2;


public final class Ex2Simulator {

  public static void main(String[] args) throws InterruptedException {
    new Ex2Simulator().run();
  }

  public static final int N_KIDS = 1000;
  public static final int N_ACTIVITIES = 5;
  public static final String[] ACTIVITIES = new String[N_ACTIVITIES];
  static {
    for (int i = 0; i < N_ACTIVITIES; i++) {
      ACTIVITIES[i] = "Activity " + i;
    }
  }

  public VoteCounter run() throws InterruptedException {
    VoteCounter voteCounter = new VoteCounter();

    Thread[] threads = new Thread[N_KIDS];
    for (int i = 0; i < N_KIDS; i++) {
      Voter kid = new Voter(ACTIVITIES, voteCounter);
      threads[i] = new Thread(kid, "Kid #" + i);
    }
    for (int i = 0; i < N_KIDS; i++) {
      threads[i].start();
    }
    for (int i = 0; i < N_KIDS; i++) {
      threads[i].join();
    }

    return voteCounter;
  }
}