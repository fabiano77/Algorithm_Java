package baekjoon;



import java.io.*;
import java.util.*;

public class Main_9659 {
   public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       long N = Long.parseLong(br.readLine());
       if(N % 2 == 1){
           System.out.println("SK");
       } else {
    	   System.out.println("CY");
       }

       br.close();
   }
}