import java.io.IOException;
import java.util.StringTokenizer;
import java.util.*;
import java.io.*;
public class coci093p3 {
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	@SuppressWarnings("unused")
	public static void main(String[]args)throws IOException{
		HashMap<Integer,Integer>hm = new HashMap<Integer,Integer>();
		int n =readInt();
		int c =  readInt();
		int nums[] = new int[n];
		for(int x =0;x<n;x++) {
			int num= readInt();
			if(hm.get(num)==null)
				hm.put(num, x);
				nums[x] = num;
		}
		ArrayList<Value> a =new ArrayList<Value>();
		Arrays.sort(nums);
		int index= 0;
		for(int x=1;x<n;x++) {
			if(nums[x]!=nums[x-1]) {
				a.add(new Value(hm.get(nums[x-1]),nums[x-1],x-index));
				index = x;
			}
		}
		a.add(new Value(hm.get(nums[n-1]),nums[n-1],n-index));
		Collections.sort(a);
		for(Value v:a) {
			for(int x= 0;x<v.count;x++)
				System.out.print(v.value+" ");
		}
	}
	static class Value implements Comparable<Value>{
		int index;
		int value;
		int count;
		Value(int index,int value,int count){
			this.index= index;
			this.value = value;
			this.count = count;
		}
		@Override
		public int compareTo(Value o) {
			if(o.count==count)
				return index-o.index;
			return o.count - count;
		}
	}
	static String next() throws IOException{
        while(st==null||!st.hasMoreTokens())
        st = new StringTokenizer(br.readLine().trim());
        return st.nextToken();
    }
    static long readLong() throws IOException{
        return Long.parseLong(next());
    }
    static int readInt() throws IOException{
        return  Integer.parseInt(next());
    }
     static double readDouble() throws IOException{
        return  Double.parseDouble(next());
    }
    static char readCharacter() throws IOException{
        return next().charAt(0);
    }
    static String readLine() throws IOException{
        return br.readLine().trim();
    }
}
