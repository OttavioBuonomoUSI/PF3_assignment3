package ex2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class VoteCounter {
  private final ConcurrentHashMap<String, AtomicInteger> votes = new ConcurrentHashMap<>();

  public void vote(String activity) {
    AtomicInteger currentCount = votes.get(activity);
    if(currentCount == null) {
      currentCount = new AtomicInteger();
    }
    votes.put(activity, new AtomicInteger(currentCount.incrementAndGet()));
  }

  public int getVoteCount(String activity) {
    AtomicInteger count = votes.get(activity);
    return count == null ? 0 : count.get();
  }
}
