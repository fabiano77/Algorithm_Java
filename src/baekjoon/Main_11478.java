package baekjoon;

import java.io.*;
import java.util.*;

public class Main_11478 {

   public static void main(String[] args) throws IOException {
      System.setIn(new FileInputStream("src/input.txt"));
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      String str = br.readLine();
      
      Set<String> set = new HashSet<>();
      
      for(int len = 1; len <= str.length(); len++) {
         for(int start = 0; start+len <= str.length(); start++) {
            set.add(str.substring(start, start+len));
         }
      }
      
      System.out.println(set.size());

      
      
      br.close();
      

   }

}
