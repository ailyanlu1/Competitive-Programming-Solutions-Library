import java.util.*;
import java.io.*;
public class syssolve {

  private static final double EPS = 1e-12;

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));

    int N = readInt();
    int M = readInt();

    double[][] A = new double[M][N];
    double[] B = new double[M];

    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++)
        A[i][j] = readDouble();
      B[i] = readDouble();
    }

    double[] ans = solve(A, B);

    if (ans == null)
      out.println("NO UNIQUE SOLUTION");
    else
      for (int i = 0; i < N; i++)
        out.println(ans[i]);

    out.close();
  }

  /*
   * Reduces the matrix A where Ax = B into a RREF and returns a unique solution
   * Input: A[][]: 	MxN matrix that represents M equations with N unknowns
   * 		  B[]:		Mx1 matrix that represents the solution to the system of equations.
   *
   * Returns: A valid unique solution or null if there is no unique solution (I.E. no solution
   * 			or multiple solutions).
   */
  public static double[] solve (double[][] A, double[] B) {
    int M = A.length;
    int N = A[0].length;

    if (N > M)
      return null;

    // forward elimination
    for (int i = 0; i < N; i++) {
      // finding the max value in column i and swapping that row to index i
      int maxIndex = i;
      for (int j = i + 1; j < M; j++)
        if (Math.abs(A[j][i]) > Math.abs(A[maxIndex][i]))
          maxIndex = j;

      if (Math.abs(A[maxIndex][i]) < EPS)
        return null;

      double[] tempA = A[i];
      A[i] = A[maxIndex];
      A[maxIndex] = tempA;

      double tempB = B[i];
      B[i] = B[maxIndex];
      B[maxIndex] = tempB;

      // ensuring that it's positive
      if (A[i][i] < 0) {
        for (int j = i; j < N; j++)
          A[i][j] *= -1;
        B[i] *= -1;
      }

      // eliminating zeroes in rows bigger than i
      for (int j = i + 1; j < M; j++) {
        double factor = A[j][i] / A[i][i];
        for (int k = i; k < N; k++)
          A[j][k] -= A[i][k] * factor;
        B[j] -= B[i] * factor;
      }
    }

    for (int i = N; i < M; i++)
      if (Math.abs(B[i]) > EPS)
        return null;

    // backward elimination
    for (int i = N - 1; i >= 0; i--) {
      if (Math.abs(A[i][i]) < EPS)
        return null;

      // eliminating zeroes in rows smaller than ix	
      for (int j = i - 1; j >= 0; j--) {
        double factor = A[j][i] / A[i][i];
        for (int k = i; k < N; k++)
          A[j][k] -= A[i][k] * factor;
        B[j] -= B[i] * factor;
      }
    }

    // dividing to create leading ones
    for (int i = 0; i < N; i++) {
      B[i] /= A[i][i];
      A[i][i] /= A[i][i];
    }

    return B;
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
