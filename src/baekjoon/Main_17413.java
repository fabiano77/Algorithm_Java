package baekjoon;

import java.io.*;
import java.util.*;

public class Main_17413 {

   public static void main(String[] args) throws IOException {
      System.setIn(new FileInputStream("src/input.txt"));
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      
      StringBuilder sb = new StringBuilder();
      
      String str = br.readLine();
      
      int startIdx = 0;
      for(int i = 0; i <= str.length(); i++) {
         if(i == str.length() || str.charAt(i) == ' ' || str.charAt(i) == '<') {
            for(int j = i-1; j >= startIdx; j--) {
               sb.append(str.charAt(j));
            }
            if(i < str.length() && str.charAt(i) == ' ') {
               sb.append(' ');
               startIdx = i+1;
            }
         }
         if(i < str.length() && str.charAt(i) == '<') {
            for(; str.charAt(i) != '>'; i++) {
               sb.append(str.charAt(i));
            }
            sb.append(str.charAt(i));
            startIdx = i+1;
         }
      }
      
      System.out.println(sb.toString());

      

      br.close();
      

   }

}
