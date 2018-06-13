import java.util.*;
import java.io.*;
public class ccc15s2p1{

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static long W;

  static long[] a;
  static TreeSet<State> ts = new TreeSet<State>();

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    N = readInt();
    W = readLong();

    a = new long[N];

    for (int i = 0; i < N; i++) {
      a[i] = readInt();

    }

    Arrays.sort(a);

    for (int i = 0; i < N; i++) {
      ts.add(new State(a[i], i));
    }

    long min = 0;
    long prev = W;
    if (W <= ts.first().value) {
      for (int i = 0; i < N; i++) {
        min += Math.min(Math.abs(prev - a[i]), Math.abs(W - a[i]));
        prev = a[i];
      }
    } else if (W >= ts.last().value) {
      for (int i = N - 1; i >= 0; i--) {
        min += Math.min(Math.abs(prev - a[i]), Math.abs(W - a[i]));
        prev = a[i];
      }
    } else {
      State lower = null;
      State higher = null;

      for (int i = N - 1; i >= 0; i--) {
        if (a[i] <= W) {
          lower = new State(a[i], i);
          break;
        }
      }

      for (int i = 0; i < N; i++) {
        if (a[i] > W) {
          higher = new State(a[i], i);
          break;
        }
      }

      prev = W;
      for (int i = lower.i; i >= 0; i--) {
        min += Math.min(Math.abs(prev - a[i]), Math.abs(W - a[i]));
        prev = a[i];
      }

      prev = W;
      for (int i = higher.i; i < N; i++) {
        min += Math.min(Math.abs(prev - a[i]), Math.abs(W - a[i]));
        prev = a[i];
      }

      long curr = 0;
      for (int i = N - 1; i >= 0; i--) {
        if (a[i] < W) {
          lower = new State(a[i], i);
          break;
        }
      }

      for (int i = 0; i < N; i++) {
        if (a[i] >= W) {
          higher = new State(a[i], i);
          break;
        }
      }

      prev = W;
      for (int i = higher.i; i < N; i++) {
        curr += Math.min(Math.abs(prev - a[i]), Math.abs(W - a[i]));
        prev = a[i];
      }

      prev = W;
      for (int i = lower.i; i >= 0; i--) {
        curr += Math.min(Math.abs(prev - a[i]), Math.abs(W - a[i]));
        prev = a[i];
      }

      min = Math.min(min, curr);
    }

    long[] loFirst = new long[N];
    long[] hiFirst = new long[N];

    int lo1 = 0;
    int hi1 = N - 1;
    int lo2 = 0;
    int hi2 = N - 1;

    boolean f = true;

    for (int i = 0; i < N; i++) {
      if (f) {
        loFirst[i] = a[lo1++];
        hiFirst[i] = a[hi2--];
      } else {
        loFirst[i] = a[hi1--];
        hiFirst[i] = a[lo2++];
      }
      f = !f;
    }

    long max = Math.max(getMax(loFirst), getMax(hiFirst));

    out.printf("%d %d\n", min, max);

    out.close();
  }

  static long getMax (long[] a) {
    long curr = 0;
    long prev = W;
    for (int i = 0; i < N; i++) {
      curr += Math.max(Math.abs(prev - a[i]), Math.abs(W - a[i]));
      prev = a[i];
    }

    long curr1 = 0;
    long prev1 = W;
    for (int i = N - 1; i >= 0; i--) {
      curr1 += Math.max(Math.abs(prev1 - a[i]), Math.abs(W - a[i]));
      prev1 = a[i];
    }
    return Math.max(curr1, curr);
  }

  static class State implements Comparable<State> {
    Long value;
    int i;

    State (long value, int i) {
      this.value = value;
      this.i = i;
    }

    @Override
    public int compareTo (State o) {
      if (value == o.value)
        return i - o.i;
      return value.compareTo(o.value);
    }

    @Override
    public boolean equals (Object o) {
      if (o instanceof State) {
        State s = (State)o;
        return value == s.value;
      }
      return false;
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

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
