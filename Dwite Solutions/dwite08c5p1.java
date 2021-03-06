import java.util.*;
import java.io.*;
    public class dwite08c5p1{
      static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
      static StringTokenizer st;
      static int s;
      public static void main(String[]args) throws IOException{
    	  for(int x = 0; x < 5; x++) {
    		  String a = readLine(), b = readLine(); int cnt = 0;
    		  for(int i = 0; i < Math.min(a.length(), b.length()); i++) {
    			  if(a.charAt(i) == b.charAt(i)) cnt++; else break;
    		  }
    		  pw.println(cnt);
    	  }
    	  pw.close();
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
