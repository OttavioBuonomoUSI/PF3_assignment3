package ex1;

import java.util.Random;

public final class Activity {

  private final String name;
  private final Random random = new Random();

  public Activity(String name) {
    this.name = name;
  }

  public void perform() throws InterruptedException {
    Thread.sleep(random.nextInt(10)); // Simulate activity time
    System.out.println(Thread.currentThread().getName() + " performed " + name);
  }

  public String getName() {
    return name;
  }
}
