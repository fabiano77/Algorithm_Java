package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main_5582 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		// Scanner sc = new Scanner(System.in);
		
		System.setIn(Main_5582.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		//String s1 = sc.next();
		String s1 = br.readLine();
		//String s2 = sc.next();
		String s2 = br.readLine();
		int cnt = 0;
		int result = 0;
//		for(int i = 0; i+cnt <= s1.length(); i++) {
//			String target = s1.substring(i, i+cnt);
//			if(s2.contains(target)) {
//				result = cnt;
//				cnt++;
//				i--;
//			}
//		}
		int [][] table = new int[s1.length()][s2.length()];
		for(int j = 0; j < s2.length(); j++) {
			if(s1.charAt(0)==s2.charAt(j)) table[0][j] = 1;			
		}
		for(int i = 0; i < s1.length(); i++) {
			if(s1.charAt(i)==s2.charAt(0)) table[i][0] = 1;			
		}
		for(int i = 1; i < s1.length(); i++) {
			for(int j = 1; j < s2.length(); j++) {
				if(s1.charAt(i) == s2.charAt(j)) {
					table[i][j] = table[i-1][j-1] + 1;
					result = (result < table[i][j])? table[i][j]:result; 
				}
			}
		}
		
		System.out.println(result);
		
	}

}
