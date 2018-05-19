import java.util.*;
import java.io.*;
public class wc15fj2{
    static BufferedReader br;
    static PrintWriter out;
    static StringTokenizer st;
    static int N;
    static String []in;
     public static void main(String []args) throws IOException{
      br = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(new OutputStreamWriter(System.out));
      N = readInt();
      in = new String[N];
      for(int i=0;i<N;i++)
      in[i] = next();
      for(int i=2;i<N;i++)
        if(in[i].equals("and")||in[i].equals("or"))
            if(in[i-2].charAt(in[i-2].length()-1)==','&&
              in[i-1].charAt(in[i-1].length()-1)!=','&&
              in[i-1].charAt(in[i-1].length()-1)!='.')
              in[i-1] +=',';
              for(int i=0;i<N;i++)
              out.print(in[i]+" ");
              out.println();
              out.close();
            
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
         return Integer.parseInt(next());
     }
     static double readDouble() throws IOException{
         return Double.parseDouble(next());
     }
     static  char readCharacter() throws IOException{
         return next().charAt(0);
     }
     static String readLine() throws IOException{
         return br.readLine().trim();
     }
}
