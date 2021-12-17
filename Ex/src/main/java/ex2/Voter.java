package ex2;


import java.util.Random;

public final class Voter implements Runnable {

  private final String[] activities;
  private final VoteCounter voteCounter;
  private final Random random = new Random();

  public Voter(String[] activities, VoteCounter voteCounter) {
    this.activities = activities;
    this.voteCounter = voteCounter;
  }

  @Override
  public void run() {
    voteCounter.vote(activities[random.nextInt(activities.length)]);
  }
}