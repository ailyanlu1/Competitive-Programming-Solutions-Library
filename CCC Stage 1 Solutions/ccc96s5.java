import java.util.*;
import java.io.*;
public class ccc96s5{
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		int length=readInt();
		for(int x =0;x<length;x++) {
			int[] pattern1 	= new int[readInt()];
			int[] pattern2 = new int[pattern1.length];
			for(int y =0;y<pattern1.length;y++)
				pattern1[y] = readInt();
			for(int y=0;y<pattern1.length;y++)
				pattern2[y] = readInt();
			int max =0;
			for(int i=0;i<pattern1.length;i++) {
				int low =0;
				int high = pattern1.length-1;
				int mid =0;
				while(low<=high) {
					mid = (low+high)/2;
					if(pattern2[mid]>=pattern1[i])
						low = mid+1;
					else
						high = mid-1;
				}
				max = Math.max(max, mid-i);
			}
			System.out.println("The maximum distance is "+max);	
		}
	}
	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens())
	   	 	st = new StringTokenizer(in.readLine().trim());
		return st.nextToken();
	}
	static int readInt() throws IOException {
		return Integer.parseInt(next());
	}
}