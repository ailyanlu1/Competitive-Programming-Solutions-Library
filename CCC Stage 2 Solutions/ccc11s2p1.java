import java.util.*;
import java.io.*;
public class ccc11s2p1{

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int n = readInt();

    Score[] scores = new Score[n];
    for (int x = 0; x < n; x++)
      scores[x] = new Score(readLong(), readDouble());
    long r = readLong();
    Arrays.sort(scores);
    scores[0].lo = 1;
    scores[0].setHi();
    if (scores[0].value == r) {
      System.out.println(scores[0].lo + "\n" + scores[0].hi);

    } else {
      for (int x = 1; x < n; x++) {
        scores[x].lo = scores[x - 1].hi + 1;
        scores[x].setHi();
        if (scores[x].value == r) {
          System.out.println(scores[x].lo + "\n" + scores[x].hi);
          break;
        }
      }
    }
  }

  static class Score implements Comparable<Score> {
    Long value;
    double average;
    int lo;
    int hi;

    Score (Long value, double average) {
      this.value = value;
      this.average = average;
    }

    @Override
    public int compareTo (Score o) {
      return o.value.compareTo(value);
    }

    private void setHi () {
      hi = (int)(2 * average - lo);
    }
  }

  static String next () throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong () throws IOException {
    return Long.parseLong(next());
  }

  static int readInt () throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble () throws IOException {
    return Double.parseDouble(next());
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
