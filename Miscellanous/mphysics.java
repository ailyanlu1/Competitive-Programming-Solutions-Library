import java.util.*;
import java.io.*;
public class mphysics {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int n = readInt();
    int k = readInt();
    Double a = 1d;
    int count = 0;
    for (int x = 0; x < k; x++) {
      a *= n;
      while (a + 0.005 >= 10) {
        count++;
        a /= 10.0;
      }
    }
    System.out.printf("%.2f\n%d", a, count);
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
