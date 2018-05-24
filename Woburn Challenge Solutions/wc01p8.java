import java.util.Scanner;

public class wc01p8{
  static Scanner scan = new Scanner(System.in);
  static final int init = 5000000;

  public static void main (String[] args) {
    for (int testCases = scan.nextInt(); testCases > 0; testCases--) {
      int numOfTimes = scan.nextInt();
      int numOfEdges = scan.nextInt();
      int[][] cost = new int[numOfTimes][numOfTimes];
      int[][] times = new int[numOfTimes][2]; // 0 is cost, 1 is visited
      for (int x = 0; x < times.length; x++)
        times[x][0] = init;
      for (int x = 0; x < numOfEdges; x++) {
        int a = scan.nextInt() - 1;
        int b = scan.nextInt() - 1;
        int c = scan.nextInt();
        if (cost[a][b] == 0 || c < cost[a][b]) {
          cost[a][b] = c;
          cost[b][a] = c;
        }
      }
      times[0][0] = 0;
      times[0][1] = 1;
      int curr = 0;
      int min;
      int index;
      do {
        min = init;
        index = 0;
        for (int x = 0; x < cost.length; x++) {
          if (times[x][1] != 1 && cost[curr][x] != 0 && cost[curr][x] < times[x][0])
            times[x][0] = cost[curr][x];
          if (times[x][1] != 1 && times[x][0] < min) {
            min = times[x][0];
            index = x;
          }
        }
        curr = index;
        times[index][1] = 1;

      } while (min != init);

      int sum = 0;
      for (int x = 1; x < times.length; x++) {
        if (times[x][0] == init) {
          sum = -1;
          break;
        } else {
          sum += times[x][0];
        }

      }
      System.out.println(sum == -1 ? "Requires more conduits" : sum);
    }
  }
}
